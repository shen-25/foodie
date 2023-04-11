package com.zengshen.mapper;

import com.zengshen.model.pojo.OrderStatus;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderStatusMapper extends Mapper<OrderStatus> {
}