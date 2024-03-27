package com.amc.services.impl;

import com.amc.mapper.ErrorMapper;
import com.amc.services.ErrorServices;
import com.amc.web.domain.ErrorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorServicesImpl implements ErrorServices {

    @Autowired
    private ErrorMapper errorMapper;

    @Override
    public int save(ErrorConfig errorConfig) {
        return errorMapper.save(errorConfig);
    }
}
