package com.zengshen.service.impl;

import com.zengshen.common.enums.BusinessExceptionEnum;
import com.zengshen.common.exception.BusinessException;
import com.zengshen.common.utils.MD5Util;
import com.zengshen.mapper.UsersMapper;
import com.zengshen.pojo.Users;
import com.zengshen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author word
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users login(String username, String password) {
        Users user = this.queryUsername(username);
        if(user == null) {
            BusinessException.display(BusinessExceptionEnum.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(MD5Util.getMD5Str(password))) {
            BusinessException.display(BusinessExceptionEnum.PASSWORD_ERROR);
        }
        return user;

    }

    @Override
    public Users queryUsername(String username) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        Users user = usersMapper.selectOneByExample(example);
        return user;
    }
}
