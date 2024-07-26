package com.amc.mapper;

import com.amc.web.domain.PerConfig;
import com.amc.web.domain.PerConfig2;
import com.amc.web.domain.WebVitalsConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PerMapper {

    /**
     * 性能监控上报
     *
     * @param perConfig
     * @return
     */
    int save(PerConfig perConfig);

    int save2(PerConfig2 perConfig2);

    List<PerConfig2> getPerformance(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<WebVitalsConfig> getWebVitals(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

}
