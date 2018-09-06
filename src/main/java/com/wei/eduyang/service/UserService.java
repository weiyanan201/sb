package com.wei.eduyang.service;

import com.wei.eduyang.domain.User;
import com.wei.eduyang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }
}
