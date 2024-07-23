package com.amc.services;

import com.amc.web.domain.ErrorConfig;
import com.amc.web.domain.RecordScreen;
import com.amc.web.domain.maptype.HourDataType;
import com.amc.web.domain.maptype.MinuteDataType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ErrorServices {
    int save(ErrorConfig errorConfig);

    HashMap<String, List<HashMap<String, Object>>> list(String pid, String startDate, String endDate);


    HashMap<String, List<HashMap<String, Object>>> listByHour(String pid, String startDate, String endDate);

    List<ErrorConfig> listByType(String pid, String startDate, String endDate, String type);

    ErrorConfig getErrorConfigById(String id);

    Map<String, Integer> getErrorCountByNum(String pid, String errorMsg, String errorId);

    List<HourDataType> getJavascriptErrorCountListByHour(String pid, String errorMsg);

    /**
     * 这里没有设置默认值
     * 那条有数据,就返回那条
     * 之前如果没有数据,我会给前端默认设置一个0
     *
     * @param pid
     * @param errorMsg
     * @param timeHour
     * @return
     */
    List<MinuteDataType> getJavascriptErrorCountByMinute(String pid, String errorMsg, String timeHour);

    RecordScreen getErrorByScreenId(String recordScreenId);
}
