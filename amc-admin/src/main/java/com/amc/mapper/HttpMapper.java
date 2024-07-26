package com.amc.mapper;

import com.amc.web.domain.HttpPOJO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HttpMapper {

    int save(List<HttpPOJO> list);

    List<HttpPOJO> getHttpErrorCountByDay(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<HttpPOJO> getStatusListGroupByErrorCode(@Param("pid") String pid, @Param("date") String date);

    List<HttpPOJO> getHttpOverflow(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<HttpPOJO> getHttpTop(@Param("pid") String pid);
}
