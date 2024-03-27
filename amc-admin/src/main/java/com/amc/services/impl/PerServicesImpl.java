package com.amc.services.impl;

import com.amc.mapper.PerMapper;
import com.amc.services.PerServices;
import com.amc.web.domain.PerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PerServicesImpl implements PerServices {

    @Autowired
    private PerMapper perMapper;

    @Override
    public int save(PerConfig perConfig) {
        return perMapper.save(perConfig);
    }
}
