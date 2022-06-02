package org.benhany.fraud.repository;

import org.benhany.fraud.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryReopsitory extends JpaRepository<FraudCheckHistory, Integer> {
}
