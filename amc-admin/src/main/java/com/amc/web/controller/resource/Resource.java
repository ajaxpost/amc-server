package com.amc.web.controller.resource;

import com.amc.core.domain.R;
import com.amc.services.ResourceServices;
import com.amc.web.domain.ResourcePOJO;
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
@Api(value = "收集器->资源信息服务", tags = "收集器->资源信息服务-->")
public class Resource {

    @Autowired
    private ResourceServices resourceServices;

    @PostMapping("/report/resource")
    @CrossOrigin
    @ApiOperation(value = "上报资源信息")
    public R<String> reportResource(@RequestBody List<ResourcePOJO> list) {

        // 过滤掉 isCache = true 的数据
//        list.removeIf(ResourcePOJO::getIsCache);
//        log.info("resource上报:{}", list);
//        if (list.isEmpty()) {
//            return R.fail("resource不存在数据,或者不存在有效数据");
//        }
        int save = resourceServices.save(list);
        if (save > 0) {
            return R.ok("resource上报成功");
        }
        return R.fail("resource上报失败");
    }

}
