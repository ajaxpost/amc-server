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

import static jdk.nashorn.internal.objects.Global.Infinity;

@Service
@Slf4j
public class ErrorServicesImpl implements ErrorServices {

    private final String DATE_STR = "day";
    private final String HOUR_STR = "hour";
    private String day_type = "yyyy-MM-dd";
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
     * @param list         错误信息
     * @param type         错误类型
     * @param map_day_type 分离的时间类型
     * @return 错误信息
     */
    private List<HashMap<String, Object>> onError(List<ErrorConfig> list, String type, String map_day_type) {
        ArrayList<HashMap<String, Object>> mapArrayList = new ArrayList<>();
        // lambda表达式排序
        list.sort((o1, o2) -> (int) (o1.getTime() - o2.getTime()));
        for (ErrorConfig error : list) {
            if (!error.getType().equals(type)) continue;
            HashMap<String, Object> map = new HashMap<>();
            String date = DayUtils.timestamptoDateString(error.getTime(), day_type);
            boolean has = false;
            Iterator<HashMap<String, Object>> iterator = mapArrayList.iterator();
            while (iterator.hasNext()) {
                HashMap<String, Object> next = iterator.next();
                if (next.get(map_day_type).equals(date)) {
                    next.put("count", (int) next.get("count") + 1);
                    iterator.remove();
                    mapArrayList.add(next);
                    has = true;
                    break;
                }
            }
            if (has) continue;
            map.put(map_day_type, date);
            map.put("count", 1);
            mapArrayList.add(map);
        }
        return mapArrayList;
    }


    private Integer pvCount(String day, String map_day_type) {
        if (DATE_STR.equals(map_day_type)) {
            List<PvPOJO> timeList = routerMapper.findTimeList(day);
            return timeList.size();
        }
        List<PvPOJO> timeList = routerMapper.findTimeListByHour(day);
        return timeList.size();
    }

    private List<HashMap<String, Object>> onErrorPer(List<HashMap<String, Object>> list, String map_day_type) {
        // JS错误发生率 = JS错误个数(一次访问页面中，所有的js错误都算一次)/PV
        ArrayList<HashMap<String, Object>> mapArrayList = new ArrayList<>();
        for (HashMap<String, Object> error : list) {
            HashMap<String, Object> map = new HashMap<>();
            String day = (String) error.get(map_day_type);
            int i = pvCount(day, map_day_type);
            if (i == Infinity || i == 0) {
                i = 1;
            }
            // JS 错误个数
            Integer count = (Integer) error.get("count");
            // JS错误个数 / 当天的PV ==> JS错误发生率
            float per = (count.floatValue() / i) * 100;
            map.put(map_day_type, day);
            map.put("per", Math.min(Math.round(per), 100));
            log.info("错误个数,{},当天pv量,{},per,{}", count, i, per);
            mapArrayList.add(map);

        }
        return mapArrayList;
    }

    private HashMap<String, List<HashMap<String, Object>>> getErrorMap(String pid, String startDate, String endDate) {
        // 当前时间段的所有错误信息,最大查询了30条
        PageHelper.startPage(1, 30);
        List<ErrorConfig> list = errorMapper.list(pid, startDate, endDate);


        List<HashMap<String, Object>> jsError = onError(list, "jsError", DATE_STR);
        List<HashMap<String, Object>> console_error = onError(list, "console_error", DATE_STR);

        HashMap<String, List<HashMap<String, Object>>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("jsError", jsError);
        stringListHashMap.put("console_error", console_error);
        List<HashMap<String, Object>> jsErrorPer = onErrorPer(jsError, DATE_STR);
        List<HashMap<String, Object>> console_errorPer = onErrorPer(console_error, DATE_STR);
        stringListHashMap.put("jsErrorPer", jsErrorPer);
        stringListHashMap.put("console_errorPer", console_errorPer);
        return stringListHashMap;
    }

    private HashMap<String, List<HashMap<String, Object>>> getErrorMapByHour(String pid, String startDate, String endDate) {
        // 当前时间段的所有错误信息,最大查询了30条
        PageHelper.startPage(1, 30);
        List<ErrorConfig> list = errorMapper.list(pid, startDate, endDate);


        List<HashMap<String, Object>> jsError = onError(list, "jsError", HOUR_STR);
        List<HashMap<String, Object>> console_error = onError(list, "console_error", HOUR_STR);

        HashMap<String, List<HashMap<String, Object>>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("jsError", jsError);
        stringListHashMap.put("console_error", console_error);
        List<HashMap<String, Object>> jsErrorPer = onErrorPer(jsError, HOUR_STR);
        List<HashMap<String, Object>> console_errorPer = onErrorPer(console_error, HOUR_STR);
        stringListHashMap.put("jsErrorPer", jsErrorPer);
        stringListHashMap.put("console_errorPer", console_errorPer);
        return stringListHashMap;
    }

    @Override
    public synchronized HashMap<String, List<HashMap<String, Object>>> list(String pid, String startDate, String endDate) {
        day_type = "yyyy-MM-dd";
        return getErrorMap(pid, startDate, endDate);
    }


    @Override
    public synchronized HashMap<String, List<HashMap<String, Object>>> listByHour(String pid, String startDate, String endDate) {
        day_type = "HH:00";
        return getErrorMapByHour(pid, startDate, endDate);
    }

    @Override
    public List<ErrorConfig> listByType(String pid, String startDate, String endDate, String type) {
        List<ErrorConfig> list = errorMapper.listByType(pid, startDate, endDate, type);
        // 降序, 大 -> 小
        list.sort((o1, o2) -> (int) (o2.getTime() - o1.getTime()));
        return list;
    }

    @Override
    public ErrorConfig getErrorConfigById(String id) {
        return errorMapper.getErrorConfigById(id);
    }
}
