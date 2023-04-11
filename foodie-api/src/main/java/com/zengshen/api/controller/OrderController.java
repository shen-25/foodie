package com.zengshen.api.controller;

import com.zengshen.common.ApiRestResponse;
import com.zengshen.common.constant.Constant;
import com.zengshen.common.constant.CookieConstant;
import com.zengshen.common.enums.PayMethod;
import com.zengshen.common.utils.CookieUtil;
import com.zengshen.model.bo.SubmitOrderBO;
import com.zengshen.model.vo.OrderVO;
import com.zengshen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody SubmitOrderBO submitOrderBO) {

        if (!submitOrderBO.getPayMethod().equals(PayMethod.WEIXIN.type)
                && !submitOrderBO.getPayMethod().equals(PayMethod.ALIPAY.type)) {
            return ApiRestResponse.errorMsg("支付方式不支持！");
        }

        // 1. 创建订单
        OrderVO orderVO = orderService.addOrder(submitOrderBO);

        // 2. 创建订单以后，移除购物车中已结算（已提交）的商品
        /**
         * 1001
         * 2002 -> 用户购买
         * 3003 -> 用户购买
         * 4004
         */
        CookieUtil.setCookie(request, response, CookieConstant.SHOP_CART, "");
        return ApiRestResponse.success(orderVO.getOrderId());
    }
}
