package com.amc.web.controller.screen;

import com.amc.services.ScreenServices;
import com.amc.web.domain.RecordScreen;
import com.amc.web.domain.Result;
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
    public Result reportScreen(@RequestBody RecordScreen recordScreen) {
        log.info("屏幕上报:{}", recordScreen);
        int save = screenServices.save(recordScreen);
        if (save == 0)
            return new Result(500, "屏幕上报失败", "fail");
        return new Result(200, "屏幕上报成功", "success");
    }
}
