package com.amc.web.controller.error;

import com.amc.core.DayUtils;
import com.amc.core.domain.R;
import com.amc.services.ErrorServices;
import com.amc.web.domain.ErrorConfig;
import com.amc.web.domain.RecordScreen;
import com.amc.web.domain.maptype.HourDataType;
import com.amc.web.domain.maptype.MinuteDataType;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(value = "错误信息服务", tags = "错误信息服务-->")
public class error {

    @Autowired
    private ErrorServices errorServices;

    @PostMapping("/report/error")
    @CrossOrigin
    @ApiOperation("上报错误信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "errorConfig", value = "错误信息", dataType = "ErrorConfig", dataTypeClass = ErrorConfig.class)
    })
    public R<String> reportError(@RequestBody ErrorConfig errorConfig) {
        log.info("错误上报:{}", errorConfig);
        int save = errorServices.save(errorConfig);
        if (save == 0) {
            return R.fail("错误上报失败");
        }


        return R.ok("错误上报成功");
    }

    @GetMapping("/error/overflow")
    @ApiOperation("获取一段时间内的错误概览信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startDate", value = "开始时间-时间戳", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endDate", value = "结束时间-时间戳", dataType = "String", dataTypeClass = String.class),
    })
    public R<Map<String, List<HashMap<String, Object>>>> errorOverflow(@RequestParam String pid,
                                                                       @RequestParam String startDate,
                                                                       @RequestParam String endDate) {
        PageHelper.startPage(1, 30);
        HashMap<String, List<HashMap<String, Object>>> map = errorServices.list(pid, startDate, endDate);
        return R.ok(map, "获取成功");

    }


    /**
     * 获取当天24小时所有错误量
     */
    @GetMapping("/error/getErrorCountListByHour")
    @ApiOperation("获取当天24小时所有错误量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class)
    })
    public R<Map<String, List<HashMap<String, Object>>>> getErrorCountListByHour(@RequestParam String pid) {
        Long startLong = DayUtils.getCurrentStartLong();
        Long endLong = DayUtils.getCurrentEndLong();

        log.info("startLong,{},endLong,{}", startLong, endLong);
        HashMap<String, List<HashMap<String, Object>>> map = errorServices.listByHour(pid, startLong.toString(), endLong.toString());

        return R.ok(map, "获取成功");
    }

    @GetMapping("/error/getErrorList")
    @ApiOperation("获取错误列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startDate", value = "开始时间-时间戳", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endDate", value = "结束时间-时间戳", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "type", value = "类型", dataType = "String", dataTypeClass = String.class, defaultValue = "",
                    example = "jsError,console_error")

    })
    public R<List<ErrorConfig>> getErrorList(@RequestParam String pid,
                                             @RequestParam String startDate,
                                             @RequestParam String endDate,
                                             @RequestParam(required = false,
                                                     defaultValue = "") String type) {
        List<ErrorConfig> list = errorServices.listByType(pid, startDate, endDate, type);

        return R.ok(list, "获取成功");
    }

    /**
     * 根据errorId获取详细信息
     * 采用RESULT风格
     *
     * @param errorId
     * @return
     */
    @GetMapping("/error/getErrorById/{errorId}")
    @ApiOperation("根据errorId获取详细信息")
    @ApiImplicitParam(name = "errorId", value = "错误id", dataType = "String", dataTypeClass = String.class)
    public R<ErrorConfig> getErrorById(@PathVariable String errorId) {
        log.info("根据errorId获取详细信息,{}", errorId);
        ErrorConfig config = errorServices.getErrorConfigById(errorId);
        return R.ok(config, "获取成功");
    }


    @GetMapping("/error/getErrorCountByNum")
    @ApiOperation(value = "获取错误数量以及影响用户数量", notes = "返回结果:  c1 错误总数量, c2 影响用户数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "errorMsg", value = "错误信息", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "errorId", value = "错误id", dataType = "String", dataTypeClass = String.class)
    })
    public R<Map<String, Integer>> getErrorCountByNum(@RequestParam String pid,
                                                      @RequestParam String errorId,
                                                      @RequestParam(required = false) String errorMsg
    ) {
        Map<String, Integer> errorCountByNum = errorServices.getErrorCountByNum(pid, errorMsg, errorId);
        return R.ok(errorCountByNum, "获取成功");
    }

    @GetMapping("/error/getJavascriptErrorCountListByHour")
    @ApiOperation("获取当天24小时所有js错误量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "errorMsg", value = "错误信息", dataType = "String", dataTypeClass = String.class)
    })
    public R<List<HourDataType>> getJavascriptErrorCountListByHour(@RequestParam String pid,
                                                                   @RequestParam String errorMsg) {
        List<HourDataType> listByHour = errorServices.getJavascriptErrorCountListByHour(pid, errorMsg);
        return R.ok(listByHour);
    }

    @GetMapping("/error/getJavascriptErrorCountByMinute")
    @ApiOperation("获取某个小时内的js错误量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "errorMsg", value = "错误信息", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "timeHour", value = "时间段->时间戳", dataType = "Integer", dataTypeClass = Integer.class, example = "1712624400000")
    })
    public R<List<MinuteDataType>> getJavascriptErrorCountByMinute(@RequestParam String pid,
                                                                   @RequestParam String errorMsg,
                                                                   @RequestParam String timeHour) {
        List<MinuteDataType> errorCountByMinute = errorServices.getJavascriptErrorCountByMinute(pid, errorMsg, timeHour);
        return R.ok(errorCountByMinute);
    }

    @GetMapping("/error/getErrorByScreenId/{recordScreenId}")
    @ApiOperation("根据recordScreenId获取详细信息")
    @ApiImplicitParam(name = "recordScreenId", value = "错误回放ID", dataType = "String", dataTypeClass = String.class)
    public R<RecordScreen> getErrorByScreenId(@PathVariable String recordScreenId) {
        return R.ok(errorServices.getErrorByScreenId(recordScreenId));
    }


}
