package com.amc.web.controller.screen;

import com.amc.core.exception.AjaxResult;
import com.amc.services.ScreenServices;
import com.amc.web.domain.RecordScreen;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
public class Screen {

    @Autowired
    private ScreenServices screenServices;

    @PostMapping("/report/screen")
    @CrossOrigin
    @ApiOperation(tags = "收集器->上报屏幕信息", value = "上报屏幕信息")
    public AjaxResult reportScreen(@RequestBody RecordScreen recordScreen) {
        log.info("屏幕上报:{}", recordScreen);
        int save = screenServices.save(recordScreen);
        if (save == 0)
            return AjaxResult.error("屏幕上报失败");
        return AjaxResult.success("屏幕上报成功");
    }
}
