package com.zengshen.service;

import com.zengshen.model.bo.RegisterBO;
import com.zengshen.model.pojo.Users;

public interface UserService {
    Users login(String username, String password);

    Users selectByUsername(String username);

    Users createUser(RegisterBO registerBO);
}
