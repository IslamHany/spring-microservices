package org.benhany.repository;

import org.benhany.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryReopsitory extends JpaRepository<FraudCheckHistory, Integer> {
}
