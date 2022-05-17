package com.springboot.app.service;

import com.springboot.app.repository.UserRepository;
import com.springboot.app.entities.User;
import com.springboot.app.service.impl.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveOrUpdateUser(User person) {
        userRepository.save(person);
    }

    @Override
    public User getUser(long id) {
        User person = null;
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            person = optional.get();
        }else{
            System.out.println("Исключение");
        }
        return person;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
