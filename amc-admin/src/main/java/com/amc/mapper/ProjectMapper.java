package com.amc.mapper;

import com.amc.web.domain.ProjectPOJO;

import java.util.List;

public interface ProjectMapper {
    List<ProjectPOJO> list(String name);

    int save(ProjectPOJO projectPOJO);
}
