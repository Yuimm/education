package com.hct.eduservice.controller;

import com.hct.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 模拟登录
 * @author: HCT
 * @create: 2021/04/25 16:28
 * @VVVersion 1.0
 **/
@Api(description = "讲师后台登陆管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {


    // login
    @ApiOperation(value = "讲师后台登录")
    @PostMapping("/login")
    public R login(){

        return R.ok().data("token","admin");
    }


    //info
    @ApiOperation(value = "讲师后台info")
    @GetMapping("/info")
    public R info(){

        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
