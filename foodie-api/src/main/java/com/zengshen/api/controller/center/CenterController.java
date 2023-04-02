package com.zengshen.api.controller.center;

import com.zengshen.common.ApiRestResponse;
import com.zengshen.model.pojo.Users;
import com.zengshen.service.center.CenterUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息3333", httpMethod = "GET")
    @GetMapping("userInfo")
    public ApiRestResponse userInfo(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {
        Users users = centerUserService.queryById(userId);
        return ApiRestResponse.success(users);
    }
}
