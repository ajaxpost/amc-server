package com.amc.web.controller.test;

import com.amc.core.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
@CrossOrigin
public class Test {
    @GetMapping("/test1")
    public R<Map<String, String>> test1() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        return R.ok(map, "获取成功");
    }

    @GetMapping("/test2")
    public R<String> test2(HttpServletResponse response) {
        response.setStatus(404);
        return R.fail("404了");
    }

}
