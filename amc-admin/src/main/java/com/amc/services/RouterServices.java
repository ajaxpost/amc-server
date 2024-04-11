package com.amc.services;

import com.amc.web.domain.CountByHour;
import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.TodayFlowPOJO;
import com.amc.web.domain.maptype.ShowNameDataType;
import com.amc.web.domain.maptype.TodayDataType;

import java.util.List;
import java.util.Map;

public interface RouterServices {
    int pvSave(PvPOJO pv);

    List<PvPOJO> findTimeList(String time); // 2024-03-29 这样的格式 ==> time

    TodayFlowPOJO getTodayFlowDataByTenMin(String pid);

    Map<String, List<TodayDataType>> uvCountForMonth(String pid, Integer timeSize);

    CountByHour getPvCountByHour(String pid, Integer scope);

    CountByHour getUvCountByHour(String pid, Integer scope);

    CountByHour getNewCustomerCountByHour(String pid, Integer scope);

    /**
     * 获取页面访问量的综合数据
     *
     * @param pid
     * @param topCount
     * @param topDays
     * @return
     */
    List<ShowNameDataType> getSimpleUrlCountOrderByCount(String pid, String topCount, String topDays);

    List<ShowNameDataType> getCityCountOrderByCount(String pid, String topCount, String topDays);

    List<ShowNameDataType> getResidenceTimeCountOrderByCount(String pid, String topCount, String topDays);

    List<ShowNameDataType> getBrowserNameCountOrderByCount(String pid, String topCount, String topDays);

}
