package com.sky.controller;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/")
@Api(tags = "访问测试")
public class RootController {
    @RequestMapping("/")
    public Result<String> test() {
        log.info("test");
        return Result.success("喵喵喵~");
    }
}
