package com.amc.services;

import com.amc.web.domain.ErrorConfig;

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

}
