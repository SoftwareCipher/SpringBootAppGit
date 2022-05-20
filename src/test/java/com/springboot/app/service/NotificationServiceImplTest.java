package com.springboot.app.service;

import com.springboot.app.entities.Notification;
import com.springboot.app.exception_handling.NoSuchEntityException;
import com.springboot.app.repository.NotificationRepository;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {NotificationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceImplTest {

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    Notification notification;

    @MockBean
    NotificationRepository notificationRepository;

    @Autowired
    NotificationServiceImpl notificationService;

    @BeforeEach
    public void setUp() {
        notification = new Notification();
        notification.setId(1);
        notification.setNotification("Execute");
        notification.setStatus("new");
    }

    @Test
    void getAllNotifications() {
        List<Notification> allNotifications = new ArrayList<>();
        when(this.notificationRepository.findAll()).thenReturn(allNotifications);

        List<Notification> actualNotifications = this.notificationRepository.findAll();
        assertSame(allNotifications, actualNotifications);
        assertTrue(actualNotifications.isEmpty());
        verify(this.notificationRepository).findAll();
    }

    @Test
    void saveOrUpdateNotification() {
        when(this.notificationRepository.save((Notification) any())).thenReturn(notification);

        Notification notification1 = new Notification();
        notification1.setId(1);
        notification1.setNotification("Execute");
        notification1.setStatus("new");
        this.notificationService.saveOrUpdateNotification(notification1);
        verify(this.notificationRepository).saveAndFlush((Notification) any());
        assertEquals(1, notification1.getId());
        assertEquals("Execute", notification1.getNotification());
        assertEquals("new", notification1.getStatus());
        assertTrue(this.notificationService.getAllNotifications().isEmpty());
    }

    @Test
    void getNotification() {
        Optional<Notification> ofResult = Optional.of(notification);
        when(this.notificationRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(notification, this.notificationService.getNotification(1));
        verify(this.notificationRepository).findById((Long) any());
    }

    @Test
    void testGetNotification2() {
        when(this.notificationRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NoSuchEntityException.class, () -> this.notificationServiceImpl
                .getNotification(123L));
        verify(this.notificationRepository).findById((Long) any());
    }

    @Test
    void deleteNotification() {
        doNothing().when(this.notificationRepository).deleteById((Long) any());
        this.notificationService.deleteNotification(1);
        verify(this.notificationRepository).deleteById((Long) any());
        assertTrue(this.notificationService.getAllNotifications().isEmpty());
    }
}