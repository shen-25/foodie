package com.zengshen.mapper;

import com.zengshen.pojo.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author word
 */
@Repository
public interface UsersMapper extends Mapper<Users> {
}