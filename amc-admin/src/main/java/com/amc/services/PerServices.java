package com.amc.services;

import com.amc.web.domain.PerConfig;
import com.amc.web.domain.PerConfig2;
import com.amc.web.domain.WebVitalsConfig;

import java.util.List;

public interface PerServices {
    int save(PerConfig perConfig);

    int save2(PerConfig2 perConfig2);

    List<PerConfig2> getPerformance(String pid, String startDate, String endDate);

    List<WebVitalsConfig> getWebVitals(String pid, String startDate, String endDate);
}
