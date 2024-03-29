package com.amc.mapper;

import com.amc.web.domain.ErrorConfig;

import java.util.List;

public interface ErrorMapper {

    /**
     * 错误上报
     *
     * @param errorConfig
     * @return
     */
    int save(ErrorConfig errorConfig);


    List<ErrorConfig> list(String pid, String startDate, String endDate);
    
}
