package com.amc.mapper;

import com.amc.web.domain.ErrorConfig;

public interface ErrorMapper {

    /**
     * 错误上报
     * @param errorConfig
     * @return
     */
    int save(ErrorConfig errorConfig);

}
