package com.zengshen.api.controller.center;

import com.zengshen.common.ApiRestResponse;
import com.zengshen.model.bo.center.CenterUserBO;
import com.zengshen.model.pojo.Users;
import com.zengshen.service.center.CenterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletRequest response;

    @Autowired
    private CenterUserService centerUserService;


    @PostMapping("update")
    public ApiRestResponse updateUser(@RequestParam String userId,
                                      @RequestBody @Valid CenterUserBO centerUserBO) {

        Users users = centerUserService.updateUserInfo(userId, centerUserBO);
        return ApiRestResponse.success(users);

    }
}
