package org.benhany.customer.service;

import lombok.RequiredArgsConstructor;
import org.benhany.amqp.producer.RabbitMQMessageProducer;
import org.benhany.clients.fraud.FraudClient;
import org.benhany.clients.fraud.dto.FraudCheckResponse;
import org.benhany.clients.notification.NotificationClient;
import org.benhany.clients.notification.dto.NotificationRequest;
import org.benhany.customer.dto.CustomerRegistrationRequest;
import org.benhany.customer.model.Customer;
import org.benhany.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FraudClient fraudClient;

    @Autowired
    NotificationClient notificationClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request){

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.getIsFraudster()) throw new IllegalStateException("fraudster");

//        notificationClient.sendNotification(
//                NotificationRequest.builder()
//                        .toCustomerId(customer.getId())
//                        .toCustomerEmail(customer.getEmail())
//                        .message(String.format("Hello my name is %s %s", customer.getFirstName(), customer.getLastName()))
//                        .build()
//        );

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message(String.format("Hello my name is %s %s", customer.getFirstName(), customer.getLastName()))
                .build();

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
                );

    }
}
