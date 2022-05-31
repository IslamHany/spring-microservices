package org.benhany.service;

import org.benhany.Repository.NotificationRepository;
import org.benhany.clients.notification.dto.NotificationRequest;
import org.benhany.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public void send(NotificationRequest request){
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(request.getToCustomerId())
                        .toCustomerEmail(request.getToCustomerEmail())
                        .message(request.getMessage())
                        .sender("benhany")
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}