package com.amc.web.controller.http;

import com.amc.core.domain.R;
import com.amc.services.HttpServices;
import com.amc.web.domain.HttpPOJO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(value = "收集器->http信息服务", tags = "收集器->http信息服务-->")
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

}
