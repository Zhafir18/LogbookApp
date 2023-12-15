package com.LogbookApp.service;

import com.LogbookApp.dto.notification.NotificationMessageDTO;
import com.LogbookApp.dto.notification.TotalNotificationDTO;
import com.LogbookApp.entity.Notification;
import com.LogbookApp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Integer getTotalNotification(String username) {
        var data = notificationRepository.getTotalNotification(username);
        return data;
    }

    public List<NotificationMessageDTO> getNotificationMessage(String username) {
        var data = notificationRepository.getMessage(username);
        var dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyy, HH:mm");
        for (var dto : data) {
            dto.setDateTimeFormat(dateFormat.format(dto.getDate()));
        }
        return data;
    }

    public void readOne(Long id) {
        var notif = notificationRepository.findById(id).get();
        notif.setIsRead(true);
        notificationRepository.save(notif);
    }

    public void readAll() {
        List<Notification> notifications = notificationRepository.findAll();

        for (var notif : notifications) {
            notif.setIsRead(true);
            notificationRepository.save(notif);
        }
    }
}
