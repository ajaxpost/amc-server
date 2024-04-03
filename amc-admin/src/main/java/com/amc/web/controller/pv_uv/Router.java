package com.amc.web.controller.pv_uv;

import com.amc.core.exception.AjaxResult;
import com.amc.services.RouterServices;
import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.TodayFlowPOJO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(value = "PV_UV信息服务", tags = "PV_UV信息服务-->")
public class Router {

    @Autowired
    private RouterServices routerServices;

    @CrossOrigin
    @PostMapping("/report/pv")
    @ApiOperation(value = "上报PV")
    public AjaxResult reportPv(@RequestBody PvPOJO pv) {
        log.info("pv上报:{}", pv);
        int i = routerServices.pvSave(pv);
        if (i > 0) {
            return AjaxResult.success("pv上报成功");
        }
        return AjaxResult.error("pv上报失败");
    }

    @GetMapping("getTodayFlowDataByTenMin")
    @ApiOperation(value = "获取流量数据-> pv,uv,新访客,ip数")
    public AjaxResult getTodayFlowDataByTenMin(@RequestParam String pid) {
        /**
         * 1. 需要返回两天的数据
         *         比如 pv 返回今天的和昨天的数据,
         *         最后需要再前端进行计算,计算得出相比较昨日的数据百分比
         */
        TodayFlowPOJO todayFlowDataByTenMin = routerServices.getTodayFlowDataByTenMin(pid);
        return AjaxResult.success(todayFlowDataByTenMin);
    }


}
