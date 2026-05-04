package com.prakash.gateaway_service.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiKey;

    private String clientName;

    private String path;

    private String method;

    private Boolean allowed;

    private Integer statusCode;

    private String reason;

    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "UsageLog{" +
                "id=" + id +
                ", apiKey='" + apiKey + '\'' +
                ", clientName='" + clientName + '\'' +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", allowed=" + allowed +
                ", statusCode=" + statusCode +
                ", reason='" + reason + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public UsageLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}