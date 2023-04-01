package com.zengshen.mapper;

import com.zengshen.model.pojo.UserAddress;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserAddressMapper extends Mapper<UserAddress> {
}