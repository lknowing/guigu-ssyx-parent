package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

/**
 * FileName: IndexController
 * Package: com.atguigu.ssyx.controller
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/07/08-14:56
 * @Version: 1.0
 */
@RestControllerAdvice
@RequestMapping("/admin/acl/index")
@CrossOrigin
@Api(tags = "登录接口")
@Slf4j
public class IndexController {

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result userLogin() {
        log.info("当前时间：{}，接收到 登录 请求", LocalDateTime.now());
        HashMap<String, String> map = new HashMap<>();
        map.put("token", UUID.randomUUID().toString());
        return Result.ok(map);
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result userInfo() {
        log.info("当前时间：{}，接收到 获取用户信息 请求", LocalDateTime.now());
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    @PostMapping("/logout")
    @ApiOperation("退出账户")
    public Result userLogout() {
        log.info("当前时间：{}，接收到 退出账户 请求", LocalDateTime.now());
        return Result.ok(null);
    }
}
