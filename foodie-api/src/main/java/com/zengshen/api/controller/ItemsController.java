package com.zengshen.api.controller;

import com.zengshen.common.ApiRestResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("items")
public class ItemsController {


    @GetMapping("/info/{itemId}")
    public ApiRestResponse info(@PathVariable String itemId) {
        return ApiRestResponse.success();
    }
}
