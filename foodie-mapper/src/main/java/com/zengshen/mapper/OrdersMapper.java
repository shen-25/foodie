package com.zengshen.mapper;

import com.zengshen.model.pojo.Orders;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrdersMapper extends Mapper<Orders> {
}