package org.benhany.service;

import org.benhany.clients.fraud.FraudClient;
import org.benhany.clients.fraud.dto.FraudCheckResponse;
import org.benhany.clients.notification.NotificationClient;
import org.benhany.clients.notification.dto.NotificationRequest;
import org.benhany.dto.CustomerRegistrationRequest;
import org.benhany.model.Customer;
import org.benhany.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FraudClient fraudClient;

    @Autowired
    NotificationClient notificationClient;

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

        notificationClient.sendNotification(
                NotificationRequest.builder()
                        .toCustomerId(customer.getId())
                        .toCustomerEmail(customer.getEmail())
                        .message(String.format("Hello my name is %s %s", customer.getFirstName(), customer.getLastName()))
                        .build()
        );

    }
}
