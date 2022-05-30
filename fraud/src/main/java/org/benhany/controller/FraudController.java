package org.benhany.controller;

import lombok.extern.slf4j.Slf4j;
import org.benhany.dto.FraudCheckResponse;
import org.benhany.service.FraudCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud")
public class FraudController {

    @Autowired
    private FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable(name = "customerId") Integer customerId){
        Boolean isFraudulentCustomer = fraudCheckService.isFraudulent(customerId);
        log.info("fraud check request for customer {}", customerId);
        return FraudCheckResponse.builder().isFraudster(isFraudulentCustomer).build();
    }
}
