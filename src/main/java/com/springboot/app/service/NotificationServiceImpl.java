package com.springboot.app.service;

import com.springboot.app.exception_handling.NoSuchEntityException;
import com.springboot.app.repository.NotificationRepository;
import com.springboot.app.entities.Notification;
import com.springboot.app.service.impl.NotificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationServiceInterface {

    NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public void saveOrUpdateNotification(Notification T) {
        notificationRepository.saveAndFlush(T);
    }

    @Override
    public Notification getNotification(long id) {
        Notification notification = null;
        Optional<Notification> optional = notificationRepository.findById(id);
        if(optional.isPresent()){
            notification = optional.get();
        }
        else{
            throw new NoSuchEntityException("There is no Notification with id = "
                    + id + " in database");
        }
        return notification;
    }

    @Override
    public void deleteNotification(long id) {
        notificationRepository.deleteById(id);
    }
}
