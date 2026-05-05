package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.DTO.AbuseAlertResponseDto;
import com.prakash.gateaway_service.Entity.AbuseAlert;
import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Repository.AbuseAlertRepository;
import com.prakash.gateaway_service.Repository.UsageLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AbuseDetectionService {

    private final UsageLogRepository usageLogRepository;
    private final AbuseAlertRepository abuseAlertRepository;

    public AbuseDetectionService(UsageLogRepository usageLogRepository, AbuseAlertRepository abuseAlertRepository) {
        this.usageLogRepository = usageLogRepository;
        this.abuseAlertRepository = abuseAlertRepository;
    }

    public void checkAndCreateAlert(Client client) {

        LocalDateTime windowStart = LocalDateTime.now().minusMinutes(5);

        long blockedCount = usageLogRepository
                .countByClientIdAndAllowedFalseAndTimestampAfter(
                        client.getId(),
                        windowStart
                );

        if(blockedCount >= 10) {
            AbuseAlert abuseAlert = new AbuseAlert();
            abuseAlert.setClient(client);
            abuseAlert.setMessage("Client exceeded blocked request threshold");
            abuseAlert.setSeverity("HIGH");
            abuseAlert.setBlockedRequestCount((int) blockedCount);
            abuseAlert.setWindowStart(windowStart);
            abuseAlert.setCreatedAt(LocalDateTime.now());


            abuseAlertRepository.save(abuseAlert);

        }
    }
    public List<AbuseAlertResponseDto> findClientAbuse(Long clientId) {
        List<AbuseAlert> alerts = abuseAlertRepository.findByClientIdOrderByCreatedAtDesc(clientId);
        List<AbuseAlertResponseDto> responseDtos = new ArrayList<>();
        for(AbuseAlert alert: alerts) {
            responseDtos.add(new AbuseAlertResponseDto(alert.getClient().getName(),
                    alert.getBlockedRequestCount(), alert.getSeverity(),
                    alert.getMessage(), alert.getWindowStart(), alert.getCreatedAt()));
        }
        return responseDtos;
    }
}
