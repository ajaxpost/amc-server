package com.amc.services;

import com.amc.web.domain.ErrorConfig;

import java.util.HashMap;
import java.util.List;

public interface ErrorServices {
    int save(ErrorConfig errorConfig);

    HashMap<String, List<HashMap<String, Object>>> list(String pid, String startDate, String endDate);

    HashMap<String, List<HashMap<String, Object>>> listByHour(String pid, String startDate, String endDate);
}
