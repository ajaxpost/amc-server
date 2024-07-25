package com.amc.web.controller.performance;

import com.amc.core.domain.R;
import com.amc.services.PerServices;
import com.amc.web.domain.PerConfig;
import com.amc.web.domain.PerConfig2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/per")
@Slf4j
@Api(value = "性能信息服务", tags = "性能信息服务-->")
public class Per {

    @Autowired
    private PerServices perServices;

    @CrossOrigin
    @PostMapping("/esc")
    @ApiOperation(value = "上报性能信息")
    public R<String> esc(@RequestBody PerConfig perConfig) {
        log.info("性能上报:{}", perConfig);
        int save = perServices.save(perConfig);
        if (save == 0) {
            return R.fail("性能上报失败");
        }
        return R.ok("性能上报成功");
    }

    @CrossOrigin
    @PostMapping("/esc2")
    @ApiOperation(value = "上报性能信息2")
    public R<String> esc2(@RequestBody PerConfig2 perConfig2) {
        log.info("性能上报2:{}", perConfig2);
        int save = perServices.save2(perConfig2);
        if (save == 0) {
            return R.fail("性能上报失败");
        }
        return R.ok("性能上报成功");
    }

    @GetMapping("/getPerformance")
    @ApiOperation(value = "获取性能信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startDate", value = "开始时间-时间戳", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endDate", value = "结束时间-时间戳", dataType = "String", dataTypeClass = String.class),
    })
    public R<List<PerConfig2>> getPerformance(@RequestParam String pid, @RequestParam String startDate, @RequestParam String endDate) {
        List<PerConfig2> performance = perServices.getPerformance(pid, startDate, endDate);
        log.info("获取性能信息:{}", performance);
        performance.sort((o1, o2) -> Math.toIntExact(o1.getTime() - o2.getTime()));
        return R.ok(performance);
    }
}
