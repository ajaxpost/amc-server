package com.amc.mapper;

import com.amc.web.domain.PvPOJO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouterMapper {

    int pvSave(PvPOJO pv);

    List<PvPOJO> findTimeList(String time); // 2024-03-29 这样的格式 ==> time

    List<PvPOJO> findTimeListByHour(String time); // 11:00

    List<PvPOJO> selectPvByDay(@Param("startDay") String startDay, @Param("endDay") String endDay);

    List<PvPOJO> selectUvByDay(@Param("startDay") String startDay, @Param("endDay") String endDay);

    List<PvPOJO> selectNewUvByDay(@Param("startDay") String startDay, @Param("endDay") String endDay);

    List<PvPOJO> selectIpByDay(@Param("startDay") String startDay, @Param("endDay") String endDay);
}
