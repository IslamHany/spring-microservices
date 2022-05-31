package org.benhany.controller;

import lombok.extern.slf4j.Slf4j;
import org.benhany.clients.notification.dto.NotificationRequest;
import org.benhany.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("New notification {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
