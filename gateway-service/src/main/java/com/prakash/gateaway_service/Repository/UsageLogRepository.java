package com.prakash.gateaway_service.Repository;

import com.prakash.gateaway_service.Entity.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    public List<UsageLog> findByClientIdOrderByTimestampDesc(Long clientId);


    long countByClientId(Long clientId);
    long countByClientIdAndAllowed(Long clientId, Boolean allowed);
}
