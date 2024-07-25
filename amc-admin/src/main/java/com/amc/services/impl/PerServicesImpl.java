package com.amc.services.impl;

import com.amc.mapper.PerMapper;
import com.amc.services.PerServices;
import com.amc.web.controller.performance.Per;
import com.amc.web.domain.PerConfig;
import com.amc.web.domain.PerConfig2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PerServicesImpl implements PerServices {

    @Autowired
    private PerMapper perMapper;
    @Autowired
    private Per per;

    @Override
    public int save(PerConfig perConfig) {
        return perMapper.save(perConfig);
    }

    @Override
    public int save2(PerConfig2 perConfig2) {
        return perMapper.save2(perConfig2);
    }

    @Override
    public List<PerConfig2> getPerformance(String pid, String startDate, String endDate) {
        return perMapper.getPerformance(pid, startDate, endDate);
    }
}
