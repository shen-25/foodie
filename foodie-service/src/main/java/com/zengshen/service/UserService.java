package com.zengshen.service;

import com.zengshen.bo.RegisterBO;
import com.zengshen.pojo.Users;

public interface UserService {
    Users login(String username, String password);

    Users selectByUsername(String username);

    Users createUser(RegisterBO registerBO);
}
