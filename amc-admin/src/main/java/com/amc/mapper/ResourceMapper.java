package com.amc.mapper;

import com.amc.web.domain.ResourcePOJO;

import java.util.List;

public interface ResourceMapper {
    int save(List<ResourcePOJO> list);
}
