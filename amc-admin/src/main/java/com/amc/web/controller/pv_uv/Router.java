package com.amc.web.controller.pv_uv;

import com.amc.services.RouterServices;
import com.amc.web.domain.PvPOJO;
import com.amc.web.domain.Result;
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
    /**
     * 上报pv
     */
    public Result reportPv(@RequestBody PvPOJO pv) {
        log.info("pv上报:{}", pv);
        int i = routerServices.pvSave(pv);
        return new Result(i > 0 ? 200 : 500, i > 0 ? "success" : "fail", null);
    }


}
