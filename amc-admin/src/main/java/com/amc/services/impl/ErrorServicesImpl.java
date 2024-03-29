package com.amc.services.impl;

import com.amc.core.DayUtils;
import com.amc.mapper.ErrorMapper;
import com.amc.mapper.RouterMapper;
import com.amc.services.ErrorServices;
import com.amc.web.domain.ErrorConfig;
import com.amc.web.domain.PvPOJO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ErrorServicesImpl implements ErrorServices {

    @Autowired
    private ErrorMapper errorMapper;

    @Autowired
    private RouterMapper routerMapper;

    @Override
    public int save(ErrorConfig errorConfig) {
        return errorMapper.save(errorConfig);
    }


    /**
     * 统计错误信息
     *
     * @param list 错误信息
     * @param type 错误类型
     * @return 错误信息
     */
    private List<HashMap<String, Object>> onError(List<ErrorConfig> list, String type) {
        ArrayList<HashMap<String, Object>> mapArrayList = new ArrayList<>();
        for (ErrorConfig error : list) {
            if (!error.getType().equals(type)) continue;
            HashMap<String, Object> map = new HashMap<>();
            String date = DayUtils.timestamptoDateString(error.getTime());
            boolean has = false;
            Iterator<HashMap<String, Object>> iterator = mapArrayList.iterator();
            while (iterator.hasNext()) {
                HashMap<String, Object> next = iterator.next();
                if (next.get("day").equals(date)) {
                    next.put("count", (int) next.get("count") + 1);
                    iterator.remove();
                    mapArrayList.add(next);
                    has = true;
                    break;
                }
            }
            if (has) continue;
            map.put("day", date);
            map.put("count", 1);
            mapArrayList.add(map);
        }
        return mapArrayList;
    }

    private Integer pvCount(String day) {
        List<PvPOJO> timeList = routerMapper.findTimeList(day);
        return timeList.size();
    }

    private List<HashMap<String, Object>> onErrorPer(List<HashMap<String, Object>> list) {
        // JS错误发生率 = JS错误个数(一次访问页面中，所有的js错误都算一次)/PV
        ArrayList<HashMap<String, Object>> mapArrayList = new ArrayList<>();
        for (HashMap<String, Object> error : list) {
            HashMap<String, Object> map = new HashMap<>();
            String day = (String) error.get("day");
            // 获取某一天的pv数量
            Integer i = pvCount(day);
            // JS 错误个数
            Integer count = (Integer) error.get("count");
            // JS错误个数 / 当天的PV ==> JS错误发生率
            float per = (count.floatValue() / i) * 100;
            map.put("day", day);
            map.put("per", Math.round(per));
            mapArrayList.add(map);

        }
        return mapArrayList;
    }

    @Override
    public HashMap<String, List<HashMap<String, Object>>> list(String pid, String startDate, String endDate) {
        // 当前时间段的所有错误信息,最大查询了30条
        PageHelper.startPage(1, 30);
        List<ErrorConfig> list = errorMapper.list(pid, startDate, endDate);


        List<HashMap<String, Object>> jsError = onError(list, "jsError");
        List<HashMap<String, Object>> console_error = onError(list, "console_error");

        HashMap<String, List<HashMap<String, Object>>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("jsError", jsError);
        stringListHashMap.put("console_error", console_error);
        List<HashMap<String, Object>> jsErrorPer = onErrorPer(jsError);
        List<HashMap<String, Object>> console_errorPer = onErrorPer(console_error);
        stringListHashMap.put("jsErrorPer", jsErrorPer);
        stringListHashMap.put("console_errorPer", console_errorPer);
        return stringListHashMap;
    }
}
