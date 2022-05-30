package org.benhany.controller;

import org.benhany.dto.FraudCheckResponse;
import org.benhany.service.FraudCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud")
public class FraudController {

    @Autowired
    private FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable(name = "customerId") Integer customerId){
        Boolean isFraudulentCustomer = fraudCheckService.isFraudulent(customerId);
        return FraudCheckResponse.builder().isFraudster(isFraudulentCustomer).build();
    }
}
