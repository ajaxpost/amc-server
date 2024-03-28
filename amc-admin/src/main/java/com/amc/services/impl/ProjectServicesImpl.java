package com.amc.services.impl;

import com.amc.mapper.ProjectMapper;
import com.amc.services.ProjectServices;
import com.amc.web.domain.ProjectPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServicesImpl implements ProjectServices {
    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public List<ProjectPOJO> list(String name) {
        return projectMapper.list(name);
    }

    @Override
    public int save(ProjectPOJO projectPOJO) {
        List<ProjectPOJO> list = list(projectPOJO.getProject_name());
        if (!list.isEmpty()) return 0;
        return projectMapper.save(projectPOJO);
    }
}
