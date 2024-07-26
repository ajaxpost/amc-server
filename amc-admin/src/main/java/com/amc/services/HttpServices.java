package com.amc.services;

import com.amc.web.domain.HttpPOJO;
import com.amc.web.domain.maptype.DayDataType;

import java.util.List;
import java.util.Map;

public interface HttpServices {
    int save(List<HttpPOJO> list);

    Map<String, List<DayDataType>> getHttpErrorCountByDay(String pid, String startDate, String endDate);

    List<Map<String, String>> getStatusListGroupByErrorCode(String pid, String date);

    List<HttpPOJO> getHttpOverflow(String pid, String startDate, String endDate);

    List<HttpPOJO> getHttpTop(String pid);

}
