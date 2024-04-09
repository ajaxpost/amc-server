package com.amc.mapper;

import com.amc.web.domain.ErrorConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErrorMapper {

    /**
     * 错误上报
     *
     * @param errorConfig
     * @return
     */
    int save(ErrorConfig errorConfig);


    List<ErrorConfig> list(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ErrorConfig> listByType(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("type") String type);

    ErrorConfig getErrorConfigById(@Param("id") String id);

    Map<String, Integer> selectErrorCountByNum(@Param("pid") String pid, @Param("errorMsg") String errorMsg, @Param("errorId") String errorId);


}
