package org.benhany.clients.fraud;

import org.benhany.clients.fraud.dto.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud") //fraud is the client name in eureka
public interface FraudClient {

    @GetMapping("/api/v1/fraud/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable(name = "customerId") Integer customerId);
}
