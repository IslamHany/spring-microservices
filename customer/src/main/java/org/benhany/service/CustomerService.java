package org.benhany.service;

import org.benhany.dto.CustomerRegistrationRequest;
import org.benhany.model.Customer;
import org.benhany.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
//                .id(UUID.randomUUID())
                .build();

        customerRepository.save(customer);
    }
}
