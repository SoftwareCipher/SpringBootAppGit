package com.springboot.app.service.impl;

import com.springboot.app.entities.Notification;

import java.util.List;

public interface NotificationServiceInterface {
    List<Notification> getAllNotifications();

    void saveOrUpdateNotification(Notification notification);

    Notification getNotification(long id);

    void deleteNotification(long id);
}
