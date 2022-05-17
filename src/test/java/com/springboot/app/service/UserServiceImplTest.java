package com.springboot.app.service;

import com.springboot.app.entities.User;
import com.springboot.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    User user;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setFio("kirill_kuznetsov");
        user.setEmail("kirill123456789@gmail.com");
        user.setPhoneNumber(123456789);
    }

    @Test
    public void testGetAllUsers() {
        List<User> allUsers = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(allUsers);
        List<User> actualAllUser = this.userRepository.findAll();
        assertSame(allUsers, actualAllUser);
        assertTrue(actualAllUser.isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    public void testSaveOrUpdateUser() {
        when(this.userRepository.save((User) any())).thenReturn(user);
        User user1 = new User();
        user1.setId(1L);
        user1.setFio("kirill_kuznetsov");
        user1.setEmail("kirill123456789@gmail.com");
        user1.setPhoneNumber(123456789);
        this.userServiceImpl.saveOrUpdateUser(user1);
        verify(this.userRepository).save((User) any());
        assertEquals(1L, user1.getId());
        assertEquals("kirill_kuznetsov", user1.getFio());
        assertEquals("kirill123456789@gmail.com", user1.getEmail());
        assertEquals(123456789, user1.getPhoneNumber());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    public void testGetUser() {
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userServiceImpl.getUser(1L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(this.userRepository).deleteById((Long) any());
        this.userServiceImpl.deleteUser(1L);
        verify(this.userRepository).deleteById((Long) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }
}