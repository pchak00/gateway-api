package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Repository.UsageLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AbuseDetectionService {

    private final UsageLogRepository usageLogRepository;

    public AbuseDetectionService(UsageLogRepository usageLogRepository) {
        this.usageLogRepository = usageLogRepository;
    }

    public boolean isSuspicious(Client client) {

        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);

        long blockedCount = usageLogRepository
                .countByClientIdAndAllowedFalseAndTimestampAfter(
                        client.getId(),
                        fiveMinutesAgo
                );

        return blockedCount >= 10;
    }
}
