package org.benhany.service;

import org.benhany.model.FraudCheckHistory;
import org.benhany.repository.FraudCheckHistoryReopsitory;
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
