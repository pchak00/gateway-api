package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.DTO.ClientStatsResponseDto;
import com.prakash.gateaway_service.Repository.UsageLogRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final UsageLogRepository usageLogRepository;

    ClientService(UsageLogRepository usageLogRepository) {
        this.usageLogRepository = usageLogRepository;
    }

    public ClientStatsResponseDto getStats(Long clientId) {

        long total = usageLogRepository.countByClientId(clientId);
        long allowed = usageLogRepository.countByClientIdAndAllowed(clientId, true);
        long blocked = usageLogRepository.countByClientIdAndAllowed(clientId, false);

        double blockRate = total == 0 ? 0 : ((double) blocked / total) * 100;

        return new ClientStatsResponseDto(clientId, total, allowed, blocked, blockRate);
    }
}
