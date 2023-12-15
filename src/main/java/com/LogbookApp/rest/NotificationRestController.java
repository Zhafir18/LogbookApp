package com.LogbookApp.rest;

import com.LogbookApp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationRestController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Object> totalNotification() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var response = notificationService.getTotalNotification(username);
        return ResponseEntity.status(200).body(response);
    }
}
