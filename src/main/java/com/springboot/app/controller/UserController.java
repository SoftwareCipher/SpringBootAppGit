package com.springboot.app.controller;

import com.springboot.app.entities.User;
import com.springboot.app.exception_handling.NoSuchEntityException;
import com.springboot.app.service.impl.UserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userApi")
@Api(tags = "UserController", description = "UserController | Rest questions")
public class UserController {

    UserServiceInterface userServiceInterface;

    @Autowired
    public UserController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @GetMapping("/users")
    @ApiOperation("Get all users")
    public List<User> getAllPersons() {
        return userServiceInterface.getAllUsers();
    }

    @GetMapping("/user/{id}")
    @ApiOperation("Save user")
    public User getPerson(@PathVariable long id){
        User user =  userServiceInterface.getUser(id);
        if(user == null){
            throw new NoSuchEntityException("There is no User with id = " + id + " in database");
        }
        return user;
    }

    @PostMapping("/user")
    @ApiOperation("Save user")
    public User savePerson(@RequestBody User person){
        userServiceInterface.saveOrUpdateUser(person);
        return person;
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation("Delete user by id")
    public String deletePerson(@PathVariable long id){
        User user = userServiceInterface.getUser(id);
        if(user == null){
            throw new NoSuchEntityException("There is no User with id = " +
                    id + " in database");
        }
        userServiceInterface.deleteUser(id);
        return "User with id = " + id + " was delete";
    }
}
