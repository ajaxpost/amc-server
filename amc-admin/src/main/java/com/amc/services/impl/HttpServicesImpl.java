package com.amc.services.impl;

import com.amc.mapper.HttpMapper;
import com.amc.services.HttpServices;
import com.amc.web.domain.HttpPOJO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HttpServicesImpl implements HttpServices {

    @Autowired
    private HttpMapper httpMapper;

    @Override
    public int save(List<HttpPOJO> list) {
        return httpMapper.save(list);
    }
}

