package org.benhany.fraud.service;

import org.benhany.fraud.model.FraudCheckHistory;
import org.benhany.fraud.repository.FraudCheckHistoryReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    @Autowired
    private FraudCheckHistoryReopsitory fraudCheckHistoryReopsitory;

    public Boolean isFraudulent(Integer customerId){
        fraudCheckHistoryReopsitory.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return false;
    }
}
