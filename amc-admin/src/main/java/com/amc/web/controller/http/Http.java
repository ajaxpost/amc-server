package com.amc.web.controller.http;

import com.amc.core.domain.R;
import com.amc.services.HttpServices;
import com.amc.web.domain.HttpPOJO;
import com.amc.web.domain.maptype.DayDataType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(value = "http信息服务", tags = "http信息服务-->")
public class Http {

    @Autowired
    private HttpServices httpServices;

    @CrossOrigin
    @PostMapping("/report/http")
    @ApiOperation(value = "上报http信息")
    public R<String> reportHttp(@RequestBody List<HttpPOJO> list) {
        int save = httpServices.save(list);
        if (save > 0) {
            return R.ok("http信息上报成功");
        }
        return R.ok("http信息上报失败");
    }

    @GetMapping("/http/getHttpErrorCountByDay")
    @ApiOperation("获取某段时间范围内接口错误信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startDate", value = "开始时间-时间戳", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endDate", value = "结束时间-时间戳", dataType = "String", dataTypeClass = String.class),
    })
    public R<Map<String, List<DayDataType>>> getHttpErrorCountByDay(@RequestParam String pid,
                                                                    @RequestParam String startDate,
                                                                    @RequestParam String endDate) {

        Map<String, List<DayDataType>> map = httpServices.getHttpErrorCountByDay(pid, startDate, endDate);
        return R.ok(map, "获取成功");
    }

    @GetMapping("/http/getStatusListGroupByErrorCode")
    @ApiOperation(("获取某个时间接口错误的状态码信息"))
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "date", value = "时间-(YY-MM-DD)", dataType = "String", dataTypeClass = String.class),
    })
    public R<List<Map<String, String>>> getStatusListGroupByErrorCode(@RequestParam String pid, @RequestParam String date) {
        List<Map<String, String>> map = httpServices.getStatusListGroupByErrorCode(pid, date);
        return R.ok(map, "获取成功");
    }
}
