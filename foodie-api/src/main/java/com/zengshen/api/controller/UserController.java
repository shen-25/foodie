package com.zengshen.api.controller;

import com.zengshen.model.bo.RegisterBO;
import com.zengshen.model.bo.UserBO;
import com.zengshen.common.ApiRestResponse;
import com.zengshen.common.enums.BusinessExceptionEnum;
import com.zengshen.common.utils.CookieUtil;
import com.zengshen.common.utils.JsonUtil;
import com.zengshen.model.pojo.Users;
import com.zengshen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("passport")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiRestResponse login(@RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        Users user = userService.login(userBO.getUsername(), userBO.getPassword());
        Users userResult = setNullProperty(user);
        CookieUtil.setCookie(request, response, "user",
                JsonUtil.objectToString(userResult), true);
        return ApiRestResponse.success(userResult);
    }

    // 保密
    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @PostMapping("/logout")
    public ApiRestResponse logout(@RequestParam String userId,
                                  HttpServletRequest request, HttpServletResponse response) {
        // 清除用户的相关信息的cookie
        CookieUtil.deleteCookie(request, response, "user");

        // TODO 用户退出登录，需要清空购物车
        // TODO 分布式会话中需要清除用户数据

        return ApiRestResponse.success();
    }

    @PostMapping("/register")
    public ApiRestResponse register(@RequestBody  RegisterBO registerBO,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        if (!registerBO.getPassword().equals(registerBO.getConfirmPassword())) {
            return ApiRestResponse.errorEnum(BusinessExceptionEnum.PASSWORD_DOES_NOT_MATCH);
        }

        Users user = userService.createUser(registerBO);

        CookieUtil.setCookie(request, response, "user",
                JsonUtil.objectToString(user), true);

        return ApiRestResponse.success(user);

    }
}
