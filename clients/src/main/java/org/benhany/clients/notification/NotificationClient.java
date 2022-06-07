package org.benhany.clients.notification;

import org.benhany.clients.notification.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification",
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    @PostMapping("/api/v1/notification")
    public void sendNotification(NotificationRequest notificationRequest);
}
