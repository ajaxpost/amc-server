package com.amc.services;

import com.amc.web.domain.ProjectPOJO;

import java.util.List;

public interface ProjectServices {

    List<ProjectPOJO> list(String name);

    int save(ProjectPOJO projectPOJO);

}
