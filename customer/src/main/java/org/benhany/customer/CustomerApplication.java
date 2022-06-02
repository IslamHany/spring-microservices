package org.benhany.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "org.benhany.amqp",
                "org.benhany.customer"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "org.benhany.clients"
)
public class CustomerApplication {

    public static void main(String[] args){
        SpringApplication.run(CustomerApplication.class, args);
    }
}
