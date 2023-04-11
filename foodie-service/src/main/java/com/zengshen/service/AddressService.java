package com.zengshen.service;

import com.zengshen.model.bo.AddressBO;
import com.zengshen.model.pojo.UserAddress;

import java.util.List;

/**
 * 统一一下
 * 新增用add
 * 更新用update
 * 删除用delete
 * 查询用 query
 */
public interface AddressService {
    void addAddress(AddressBO addressBO);

    List<UserAddress> queryByUserId(String userId);

   void updateDefaultAddress(String userId, String addressId);

    void deleteAddress(String addressId);

    void updateUserAddress(AddressBO addressBO);

    UserAddress queryUserAddress(String userId, String addressId);
}
