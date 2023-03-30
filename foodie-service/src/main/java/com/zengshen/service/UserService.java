package com.zengshen.service;

import com.zengshen.pojo.Users;

public interface UserService {
    Users login(String username, String password);

    Users queryUsername(String username);
}
