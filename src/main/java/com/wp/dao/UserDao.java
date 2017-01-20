package com.wp.dao;

import com.wp.model.User;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
public interface UserDao {

    User getUserByUsername(String username);

    void addUser(User user);
}
