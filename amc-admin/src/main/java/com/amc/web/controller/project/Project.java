package com.amc.web.controller.project;

import com.amc.core.domain.R;
import com.amc.core.domain.TableDataInfo;
import com.amc.services.ProjectServices;
import com.amc.web.domain.ProjectPOJO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(value = "项目信息服务", tags = "项目信息服务-->")
public class Project {

    @Autowired
    private ProjectServices projectServices;

    @GetMapping("/getProject")
    @ApiOperation(value = "获取项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "项目名称", dataType = "String", dataTypeClass = String.class)
    })
    public TableDataInfo<ProjectPOJO> getProject(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                 @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectPOJO> list = projectServices.list(name);
        PageInfo<ProjectPOJO> pageInfo = new PageInfo<>(list);
        TableDataInfo<ProjectPOJO> dataInfo = new TableDataInfo<>();
        dataInfo.setCode(200);
        dataInfo.setData(list);
        dataInfo.setTotal(pageInfo.getTotal());
        return dataInfo;
    }

    @PostMapping("/saveProject")
    @ApiOperation(value = "新建项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectPOJO", value = "项目信息", dataType = "ProjectPOJO", dataTypeClass = ProjectPOJO.class)
    })
    public R<String> saveProject(@RequestBody ProjectPOJO projectPOJO) {
        int save = projectServices.save(projectPOJO);
        if (save == 0) {
            return R.fail();
        }
        return R.ok();
    }

}
