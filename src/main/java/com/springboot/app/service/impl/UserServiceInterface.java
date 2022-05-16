package com.springboot.app.service.impl;

import com.springboot.app.entities.Person;

import java.util.List;

public interface UserServiceInterface {
    List<Person> getAllUsers();

    void saveOrUpdateUser(Person person);

    Person getUser(long id);

    void deleteUser(long id);
}
