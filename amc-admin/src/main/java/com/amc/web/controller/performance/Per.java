package com.amc.web.controller.performance;

import com.amc.services.PerServices;
import com.amc.web.domain.PerConfig;
import com.amc.web.domain.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/per")
@Slf4j
public class Per {

    @Autowired
    private PerServices perServices;

    @CrossOrigin
    @PostMapping("/esc")
    @ApiOperation(tags = "收集器->上报性能信息", value = "上报性能信息")
    public Result esc(@RequestBody PerConfig perConfig) {
        log.info("性能上报:{}", perConfig);
        int save = perServices.save(perConfig);
        if (save == 0) {
            return new Result(500, "性能上报失败", "fail");
        }
        return new Result(200, "性能上报成功", "success");
    }
}
