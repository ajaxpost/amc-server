package com.amc.web.controller.error;

import com.amc.services.ErrorServices;
import com.amc.web.domain.ErrorConfig;
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
public class error {

    @Autowired
    private ErrorServices errorServices;

    @PostMapping("/report/error")
    public Result<String> reportError(@RequestBody ErrorConfig errorConfig){
        log.info("错误上报:{}",errorConfig);
        int save = errorServices.save(errorConfig);
        if(save == 0){
            return new Result<>(500,"错误上报失败","fail");
        }

        return new Result<>(200,"错误上报成功","success");
    }

}
