package com.amc.web.controller.pv_uv;

import com.amc.core.domain.R;
import com.amc.services.RouterServices;
import com.amc.web.domain.CountByHour;
import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.TodayFlowPOJO;
import com.amc.web.domain.maptype.ShowNameDataType;
import com.amc.web.domain.maptype.TodayDataType;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(value = "PV_UV信息服务", tags = "PV_UV信息服务-->")
public class Router {

    @Autowired
    private RouterServices routerServices;

    @CrossOrigin
    @PostMapping("/report/pv")
    @ApiOperation(value = "上报PV")
    public R<String> reportPv(@RequestBody PvPOJO pv) {
        log.info("pv上报:{}", pv);
        int i = routerServices.pvSave(pv);
        if (i > 0) {
            return R.ok("pv上报成功");
        }
        return R.fail("pv上报失败");
    }

    @GetMapping("getTodayFlowDataByTenMin")
    @ApiOperation(value = "获取流量数据-> pv,uv,新访客,ip数", notes = "概览界面的流量数据")
    public R<TodayFlowPOJO> getTodayFlowDataByTenMin(@RequestParam String pid) {
        /**
         * 1. 需要返回两天的数据
         *         比如 pv 返回今天的和昨天的数据,
         *         最后需要再前端进行计算,计算得出相比较昨日的数据百分比
         *
         */
        TodayFlowPOJO todayFlowDataByTenMin = routerServices.getTodayFlowDataByTenMin(pid);
        return R.ok(todayFlowDataByTenMin);
    }

    @GetMapping("uvCountForMonth")
    @ApiOperation(value = "获取某一段时间的的uv,newUv数据", notes = "概览界面的用户量统计图表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeSize", value = "时间段", required = true, dataType = "Integer", example = "30")
    })
    public R<Map<String, List<TodayDataType>>> uvCountForMonth(@RequestParam String pid,
                                                               @RequestParam Integer timeSize) {
        Map<String, List<TodayDataType>> map = routerServices.uvCountForMonth(pid, timeSize);
        return R.ok(map);
    }


    @GetMapping("getPvCountByHour")
    @ApiOperation(value = "获取某一段时间的的pv数据", notes = "概览界面的页面访问量趋势")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "scope", value = "时间范围,比如与一周之前的数据进行比较,那么scope=7", required = true, dataType = "Integer", example = "7")
    })
    public R<CountByHour> getPvCountByHour(@RequestParam String pid,
                                           @RequestParam Integer scope) {

        CountByHour pvCountByHour = routerServices.getPvCountByHour(pid, scope);
        return R.ok(pvCountByHour);
    }

    @GetMapping("getUvCountByHour")
    @ApiOperation(value = "获取某一段时间的的uv数据", notes = "概览界面的用户活跃量趋势")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "scope", value = "时间范围,比如与一周之前的数据进行比较,那么scope=7", required = true, dataType = "Integer", example = "7")
    })
    public R<CountByHour> getUvCountByHour(@RequestParam String pid,
                                           @RequestParam Integer scope) {
        CountByHour uvCountByHour = routerServices.getUvCountByHour(pid, scope);
        return R.ok(uvCountByHour);
    }

    @GetMapping("getNewCustomerCountByHour")
    @ApiOperation(value = "获取某一段时间的的新用户数据", notes = "概览界面的新用户量活跃趋势")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "scope", value = "时间范围,比如与一周之前的数据进行比较,那么scope=7", required = true, dataType = "Integer", example = "7")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败")
    })
    public R<CountByHour> getNewCustomerCountByHour(@RequestParam String pid,
                                                    @RequestParam Integer scope) {
        CountByHour newCustomerCountByHour = routerServices.getNewCustomerCountByHour(pid, scope);
        return R.ok(newCustomerCountByHour);
    }

    @GetMapping("getSimpleUrlCountOrderByCount")
    @ApiOperation(value = "获取某一段时间的的页面访问量排行", notes = "概览界面的页面访问量排行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "topCount", value = "top多少条", required = true, dataType = "String", example = "10"),
            @ApiImplicitParam(name = "topDays", value = "时间范围,比如1天,7天,30天", required = true, dataType = "String", example = "1")
    })
    public R<List<ShowNameDataType>> getSimpleUrlCountOrderByCount(@RequestParam String pid,
                                                                   @RequestParam String topCount,
                                                                   @RequestParam String topDays) {
        List<ShowNameDataType> simpleUrlCountOrderByCount = routerServices.getSimpleUrlCountOrderByCount(pid, topCount, topDays);

        return R.ok(simpleUrlCountOrderByCount);
    }

    @GetMapping("getCityCountOrderByCount")
    @ApiOperation(value = "获取某一段时间的的城市访问量排行", notes = "概览界面的城市访问量排行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "topCount", value = "top多少条", required = true, dataType = "String", example = "10"),
            @ApiImplicitParam(name = "topDays", value = "时间范围,比如1天,7天,30天", required = true, dataType = "String", example = "1")
    })
    public R<List<ShowNameDataType>> getCityCountOrderByCount(@RequestParam String pid,
                                                              @RequestParam String topCount,
                                                              @RequestParam String topDays) {
        List<ShowNameDataType> cityCountOrderByCount = routerServices.getCityCountOrderByCount(pid, topCount, topDays);
        return R.ok(cityCountOrderByCount);
    }

    @GetMapping("getResidenceTimeCountOrderByCount")
    @ApiOperation(value = "获取某一段时间的的停留时长排行", notes = "概览界面的停留时长排行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "topCount", value = "top多少条", required = true, dataType = "String", example = "10"),
            @ApiImplicitParam(name = "topDays", value = "时间范围,比如1天,7天,30天", required = true, dataType = "String", example = "1")
    })
    public R<List<ShowNameDataType>> getResidenceTimeCountOrderByCount(String pid, String topCount, String topDays) {
        List<ShowNameDataType> residenceTimeCountOrderByCount = routerServices.getResidenceTimeCountOrderByCount(pid, topCount, topDays);
        return R.ok(residenceTimeCountOrderByCount);
    }

    @GetMapping("getBrowserNameCountOrderByCount")
    @ApiOperation(value = "获取某一段时间的的浏览器访问量排行", notes = "概览界面的浏览器访问量排行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "topCount", value = "top多少条", required = true, dataType = "String", example = "10"),
            @ApiImplicitParam(name = "topDays", value = "时间范围,比如1天,7天,30天", required = true, dataType = "String", example = "1")
    })
    public R<List<ShowNameDataType>> getBrowserNameCountOrderByCount(String pid, String topCount, String topDays) {
        List<ShowNameDataType> browserNameCountOrderByCount = routerServices.getBrowserNameCountOrderByCount(pid, topCount, topDays);
        return R.ok(browserNameCountOrderByCount);
    }
}
