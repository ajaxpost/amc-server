package com.amc.services.impl;

import com.amc.mapper.ResourceMapper;
import com.amc.services.ResourceServices;
import com.amc.web.domain.ResourcePOJO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResourceServicesImpl implements ResourceServices {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public int save(List<ResourcePOJO> list) {
        return resourceMapper.save(list);
    }
}
