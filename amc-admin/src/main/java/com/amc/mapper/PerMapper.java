package com.amc.mapper;

import com.amc.web.domain.PerConfig;

public interface PerMapper {

    /**
     * 性能监控上报
     * @param perConfig
     * @return
     */
    int save(PerConfig perConfig);

}
