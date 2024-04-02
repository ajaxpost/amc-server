package com.amc.web.controller.pv_uv;

import com.amc.core.exception.AjaxResult;
import com.amc.services.RouterServices;
import com.amc.web.domain.PvPOJO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Router {

    @Autowired
    private RouterServices routerServices;

    @CrossOrigin
    @PostMapping("/report/pv")
    @ApiOperation(tags = "收集器->上报PV", value = "上报PV")
    public AjaxResult reportPv(@RequestBody PvPOJO pv) {
        log.info("pv上报:{}", pv);
        int i = routerServices.pvSave(pv);
        if (i > 0) {
            return AjaxResult.success("pv上报成功");
        }
        return AjaxResult.error("pv上报失败");
    }


}
