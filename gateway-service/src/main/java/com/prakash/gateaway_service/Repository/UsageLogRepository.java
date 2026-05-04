package com.prakash.gateaway_service.Repository;

import com.prakash.gateaway_service.Entity.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    public List<UsageLog> findByClientIdOrderByTimestampDesc(Long clientId);


    long countByClientId(Long clientId);
    long countByClientIdAndAllowed(Long clientId, Boolean allowed);
}
