package com.zengshen.service.impl;

import com.zengshen.base.BaseInfoProperties;
import com.zengshen.model.bo.RegisterBO;
import com.zengshen.common.enums.BusinessExceptionEnum;
import com.zengshen.common.enums.Sex;
import com.zengshen.common.exception.BusinessException;
import com.zengshen.common.utils.DateUtil;
import com.zengshen.common.utils.MD5Util;
import com.zengshen.mapper.UsersMapper;
import com.zengshen.model.pojo.Users;
import com.zengshen.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author word
 */
@Service
public class UserServiceImpl extends BaseInfoProperties implements UserService{

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Override
    public Users login(String username, String password) {
        Users user = this.selectByUsername(username);
        if(user == null) {
            BusinessException.display(BusinessExceptionEnum.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(MD5Util.getMD5Str(password))) {
            BusinessException.display(BusinessExceptionEnum.PASSWORD_ERROR);
        }
        return user;

    }

    @Override
    public Users selectByUsername(String username) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return usersMapper.selectOneByExample(example);
    }

    @Override
    public Users createUser(RegisterBO registerBO) {
        Users tempUser = this.selectByUsername(registerBO.getUsername());
        if (tempUser != null) {
            BusinessException.display(BusinessExceptionEnum.USER_IS_EXIST);
        }

        Users user = new Users();
        user.setId(sid.nextShort());
        user.setUsername(registerBO.getUsername());
        String md5Str = MD5Util.getMD5Str(registerBO.getPassword());
        user.setPassword(md5Str);
        // 默认用户昵称同用户名
        user.setNickname(registerBO.getUsername());
        // 默认头像
        user.setFace(USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        user.setSex(Sex.secret.getType());

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        usersMapper.insert(user);
        return user;
    }
}
