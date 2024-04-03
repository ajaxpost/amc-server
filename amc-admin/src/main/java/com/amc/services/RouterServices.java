package com.amc.services;

import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.TodayFlowPOJO;

import java.util.List;

public interface RouterServices {
    int pvSave(PvPOJO pv);

    List<PvPOJO> findTimeList(String time); // 2024-03-29 这样的格式 ==> time

    TodayFlowPOJO getTodayFlowDataByTenMin(String pid);
}
