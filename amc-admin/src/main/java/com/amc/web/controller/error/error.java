package com.amc.web.controller.error;

import com.amc.core.DayUtils;
import com.amc.services.ErrorServices;
import com.amc.web.domain.ErrorConfig;
import com.amc.web.domain.Result;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class error {

    @Autowired
    private ErrorServices errorServices;

    @PostMapping("/report/error")
    @CrossOrigin
    @ApiOperation(tags = "收集器->上报错误信息", value = "上报错误信息")
    public Result reportError(@RequestBody ErrorConfig errorConfig) {
        log.info("错误上报:{}", errorConfig);
        int save = errorServices.save(errorConfig);
        if (save == 0) {
            return new Result(500, "错误上报失败", "fail");
        }

        return new Result(200, "错误上报成功", "success");
    }

    @GetMapping("/error/overflow")
    @ApiOperation(value = "获取一段时间内的错误概览信息", notes = "根据PID,开始时间,结束时间获取错误信息概览", tags = {"连接器->错误信息统计"})
    public Result errorOverflow(@RequestParam String pid,
                                @RequestParam String startDate,
                                @RequestParam String endDate) {
        PageHelper.startPage(1, 30);
        HashMap<String, List<HashMap<String, Object>>> map = errorServices.list(pid, startDate, endDate);
        return new Result(200, "获取成功", map);
    }


    /**
     * 获取当天24小时所有错误量
     */
    @GetMapping("/error/getErrorCountListByHour")
    @ApiOperation(value = "获取当天24小时所有错误量", notes = "根据项目ID获取当天24小时所有错误量", tags = {"连接器->错误信息统计"})
    public Result getErrorCountListByHour(@RequestParam String pid) {
        Long startLong = DayUtils.getCurrentStartLong();
        Long endLong = DayUtils.getCurrentEndLong();

        log.info("startLong,{},endLong,{}", startLong, endLong);
        HashMap<String, List<HashMap<String, Object>>> map = errorServices.listByHour(pid, startLong.toString(), endLong.toString());

        return new Result(200, "获取成功", map);
    }

}
