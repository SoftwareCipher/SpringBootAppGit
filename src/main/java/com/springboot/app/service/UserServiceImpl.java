package com.springboot.app.service;

import com.springboot.app.repository.UserRepository;
import com.springboot.app.entities.Person;
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
    public List<Person> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveOrUpdateUser(Person person) {
        userRepository.save(person);
    }

    @Override
    public Person getUser(long id) {
        Person person = null;
        Optional<Person> optional = userRepository.findById(id);
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
