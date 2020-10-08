package com.yd.service;

import com.yd.model.User;

public interface IUserService {
    int register(User user);

    User getUser(String username);
}
