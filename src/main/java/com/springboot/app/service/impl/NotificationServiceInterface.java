package com.springboot.app.service.impl;

import com.springboot.app.entities.Notification;

import java.util.List;

public interface NotificationServiceInterface {
    List<Notification> getAllEntities();

    void saveOrUpdateEntity(Notification notification);

    Notification getEntity(long id);

    void deleteEntity(long id);
}
