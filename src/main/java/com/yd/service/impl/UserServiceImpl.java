package com.yd.service.impl;

import com.yd.dao.UserMapper;
import com.yd.model.User;
import com.yd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getUser(String username) {
        return userMapper.selectByUsername(username);
    }
}
