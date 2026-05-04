package com.prakash.gateaway_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String apiKey;

    private Integer requestsPerMinute;

    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonIgnore
    private List<UsageLog> usageLogs = new ArrayList<>();

    public List<UsageLog> getUsageLogs() {
        return usageLogs;
    }

    public void setUsageLogs(List<UsageLog> usageLogs) {
        this.usageLogs = usageLogs;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", requestsPerMinute=" + requestsPerMinute +
                ", active=" + active +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getRequestsPerMinute() {
        return requestsPerMinute;
    }

    public void setRequestsPerMinute(Integer requestsPerMinute) {
        this.requestsPerMinute = requestsPerMinute;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public Client() {
    }



}