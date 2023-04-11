package com.zengshen.service;

import com.zengshen.model.bo.SubmitOrderBO;
import com.zengshen.model.vo.OrderVO;

public interface OrderService {
    public OrderVO addOrder(SubmitOrderBO submitOrderBO);


}
