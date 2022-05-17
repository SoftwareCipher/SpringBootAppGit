package com.springboot.app.service.impl;

import com.springboot.app.entities.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUsers();

    void saveOrUpdateUser(User person);

    User getUser(long id);

    void deleteUser(long id);
}
