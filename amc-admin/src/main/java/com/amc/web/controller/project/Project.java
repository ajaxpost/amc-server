package com.amc.web.controller.project;

import com.amc.services.ProjectServices;
import com.amc.web.domain.ProjectPOJO;
import com.amc.web.domain.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class Project {

    @Autowired
    private ProjectServices projectServices;

    @GetMapping("/getProject")
    @ApiOperation(value = "获取项目列表", notes = "获取项目列表", tags = {"连接器->获取项目信息"})
    public Result getProject(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectPOJO> list = projectServices.list(name);
        PageInfo<ProjectPOJO> pageInfo = new PageInfo<>(list);
        return new Result(200, "查询成功", list, pageInfo.getTotal());
    }

    @PostMapping("/saveProject")
    @ApiOperation(value = "新建项目", notes = "新建项目", tags = {"连接器->保存项目信息"})
    public Result saveProject(@RequestBody ProjectPOJO projectPOJO) {
        int save = projectServices.save(projectPOJO);
        if (save == 0) {
            return new Result(500, "操作失败", "fail");
        }
        return new Result(200, "操作成功", "success");
    }

}
