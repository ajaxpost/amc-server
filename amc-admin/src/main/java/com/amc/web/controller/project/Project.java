package com.amc.web.controller.project;

import com.amc.core.TableDataInfo;
import com.amc.core.exception.AjaxResult;
import com.amc.services.ProjectServices;
import com.amc.web.domain.ProjectPOJO;
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
    public TableDataInfo getProject(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                    @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectPOJO> list = projectServices.list(name);
        PageInfo<ProjectPOJO> pageInfo = new PageInfo<>(list);
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.setCode(200);
        dataInfo.setData(list);
        dataInfo.setTotal(pageInfo.getTotal());
        return dataInfo;
    }

    @PostMapping("/saveProject")
    @ApiOperation(value = "新建项目", notes = "新建项目", tags = {"连接器->保存项目信息"})
    public AjaxResult saveProject(@RequestBody ProjectPOJO projectPOJO) {
        int save = projectServices.save(projectPOJO);
        if (save == 0) {
            return AjaxResult.error("新建项目失败");
        }
        return AjaxResult.success("新建项目成功");
    }

}
