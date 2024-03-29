package com.amc.services.impl;

import com.amc.mapper.ProjectMapper;
import com.amc.services.ProjectServices;
import com.amc.web.domain.ProjectPOJO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectServicesImpl implements ProjectServices {
    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public ProjectPOJO findId(String id) {
        return projectMapper.findId(id);
    }

    @Override
    public List<ProjectPOJO> list(String name) {
        return projectMapper.list(name);
    }

    @Override
    public int save(ProjectPOJO projectPOJO) {
        ProjectPOJO pojo = findId(projectPOJO.getProject_id());
        log.info("pojo:{}", pojo);
        if (pojo != null) return 0;
        return projectMapper.save(projectPOJO);
    }
}
