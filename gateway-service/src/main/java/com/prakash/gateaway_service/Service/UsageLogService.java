package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Entity.UsageLog;
import com.prakash.gateaway_service.Repository.UsageLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsageLogService {

    private final UsageLogRepository usageLogRepository;

    public UsageLogService(UsageLogRepository usageLogRepository) {
        this.usageLogRepository = usageLogRepository;
    }

    public void log(Client client,
                    String path,
                    String method,
                    boolean allowed,
                    int statusCode,
                    String reason) {

        UsageLog usageLog = new UsageLog();

        usageLog.setApiKey(client.getApiKey());
        usageLog.setClientName(client.getName());
        usageLog.setPath(path);
        usageLog.setMethod(method);
        usageLog.setAllowed(allowed);
        usageLog.setStatusCode(statusCode);
        usageLog.setReason(reason);
        usageLog.setTimestamp(LocalDateTime.now());

        usageLogRepository.save(usageLog);
    }
}
