package com.wp.service;

import com.wp.dao.UserDao;
import com.wp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }

    public void addUser(User user){
        userDao.addUser(user);
    }
}
