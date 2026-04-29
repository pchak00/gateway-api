package com.prakash.backend_service.ResponseBody;

import java.math.BigDecimal;

public class OrderResponse {
    private Long id;
    private String name;
    private double total;

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderResponse(Long id, String name, double total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }
}