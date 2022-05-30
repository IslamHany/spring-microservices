package org.benhany.dto;

import lombok.Data;

@Data
public class CustomerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer id;
}
