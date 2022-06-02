package org.benhany.notification;

import org.benhany.amqp.producer.RabbitMQMessageProducer;
import org.benhany.notification.configuration.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "org.benhany.amqp",
                "org.benhany.notification"
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args){
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig) {
//        return args -> {
//            producer.publish(
//                    "fooooo",
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
}
