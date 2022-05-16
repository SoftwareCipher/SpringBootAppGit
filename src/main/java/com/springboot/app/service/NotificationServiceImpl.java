package com.springboot.app.service;

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
    public List<Notification> getAllEntities() {
        return notificationRepository.findAll();
    }

    @Override
    public void saveOrUpdateEntity(Notification T) {
        notificationRepository.saveAndFlush(T);
    }

    @Override
    public Notification getEntity(long id) {
        Notification notification = null;
        Optional<Notification> optional = notificationRepository.findById(id);
        if(optional.isPresent()){
            notification = optional.get();
        }
        else{
            System.out.println("Исключение");
        }
        return notification;
    }

    @Override
    public void deleteEntity(long id) {
        notificationRepository.deleteById(id);
    }
}
