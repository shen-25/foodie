package com.zengshen.api.controller;

import com.zengshen.base.BaseInfoProperties;
import com.zengshen.common.ApiRestResponse;
import com.zengshen.common.utils.JsonUtil;
import com.zengshen.model.bo.ShopCartBO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shopcart")
public class ShopCartController extends BaseInfoProperties {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("add")
    public ApiRestResponse add(
            @RequestParam String userId,
            @RequestBody ShopCartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StringUtils.isBlank(userId)) {
            return ApiRestResponse.errorMsg("用户不能为空");
        }

        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        String cartListStr = redisTemplate.opsForValue().get(CART_USER_ID + ":" + userId);
        List<ShopCartBO> shopCartBOListTemp = null;
        if (StringUtils.isNotBlank(cartListStr)) {
            shopCartBOListTemp = JsonUtil.jsonToList(cartListStr, ShopCartBO.class);
        }else {
            shopCartBOListTemp =  new ArrayList<>();
        }
        boolean isHaving = false;
        for (ShopCartBO temp : shopCartBOListTemp) {
            if (temp.getSpecId().equals(shopcartBO.getSpecId())) {
                temp.setBuyCounts(shopcartBO.getBuyCounts() + temp.getBuyCounts());
                isHaving = true;
                break;
            }
        }
        if (!isHaving) {
            shopCartBOListTemp.add(shopcartBO);
        }
        redisTemplate.opsForValue().set(CART_USER_ID + ":" + userId, JsonUtil.objectToString(shopCartBOListTemp));
        return ApiRestResponse.success();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("del")
    public ApiRestResponse del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return ApiRestResponse.errorMsg("参数不能为空");
        }

        // TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品

        return ApiRestResponse.success();
    }

}
