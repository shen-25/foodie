package com.zengshen.api.controller;

import com.zengshen.bo.UserBO;
import com.zengshen.common.ApiRestResponse;
import com.zengshen.pojo.Users;
import com.zengshen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        return ApiRestResponse.success(user);

    }
}
