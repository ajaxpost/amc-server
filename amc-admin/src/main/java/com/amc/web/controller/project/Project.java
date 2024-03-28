package com.amc.web.controller.project;

import com.amc.services.ProjectServices;
import com.amc.web.domain.ProjectPOJO;
import com.amc.web.domain.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public Result getProject(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectPOJO> list = projectServices.list(name);
        PageInfo<ProjectPOJO> pageInfo = new PageInfo<>(list);
        return new Result(200, "查询成功", list, pageInfo.getTotal());
    }

    @PostMapping("/saveProject")
    public Result saveProject(@RequestBody ProjectPOJO projectPOJO) {
        int save = projectServices.save(projectPOJO);
        // @TODO 还未测试
        if (save == 0) {
            return new Result(500, "操作失败", "fail");
        }
        return new Result(200, "操作成功", "success");
    }

}
