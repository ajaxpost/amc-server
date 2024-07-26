package com.amc.mapper;

import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.maptype.HourDataType;
import com.amc.web.domain.maptype.NameDataType;
import com.amc.web.domain.maptype.ShowNameDataType;
import com.amc.web.domain.maptype.TodayDataType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouterMapper {

    int pvSave(PvPOJO pv);

    List<PvPOJO> findTimeList(String time); // 2024-03-29 这样的格式 ==> time

    List<PvPOJO> findTimeListByHour(String time); // 11:00

    List<TodayDataType> selectPvByDay(@Param("pid") String pid, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<TodayDataType> selectUvByDay(@Param("pid") String pid, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<TodayDataType> selectNewUvByDay(@Param("pid") String pid, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<TodayDataType> selectIpByDay(@Param("pid") String pid, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<HourDataType> selectPvByHour(@Param("pid") String pid, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<HourDataType> selectUvByHour(@Param("pid") String pid, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<HourDataType> selectNewUvByHour(@Param("pid") String pid, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<ShowNameDataType> selectSimpleUrlCountOrderByCount(@Param("pid") String pid, @Param("topCount") Integer topCount, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<ShowNameDataType> selectCityCountOrderByCount(@Param("pid") String pid, @Param("topCount") Integer topCount, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<ShowNameDataType> selectResidenceTimeCountOrderByCount(@Param("pid") String pid, @Param("topCount") Integer topCount, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<ShowNameDataType> selectBrowserNameCountOrderByCount(@Param("pid") String pid, @Param("topCount") Integer topCount, @Param("startDay") String startDay, @Param("endDay") String endDay);

    List<TodayDataType> getPv(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<NameDataType> getVisitPage(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

}
