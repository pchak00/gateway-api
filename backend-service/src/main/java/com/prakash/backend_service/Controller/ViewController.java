package com.prakash.backend_service.Controller;

import com.prakash.backend_service.ResponseBody.OrderResponse;
import com.prakash.backend_service.ResponseBody.ProductResponse;
import com.prakash.backend_service.ResponseBody.ReportResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ViewController {

    @GetMapping("/products")
    public List<ProductResponse> getProducts() {
        return List.of(
                new ProductResponse(1L, "Laptop", 1299.99),
                new ProductResponse(2L, "Keyboard", 89.99),
                new ProductResponse(3L, "Mouse", 49.99)
        );
    }

    @GetMapping("/orders")
    public List<OrderResponse> getOrders() {
        return List.of(
                new OrderResponse(1L, "Alice", 499.99),
                new OrderResponse(2L, "Bob", 899.99)
        );
    }

    @GetMapping("/reports")
    public ReportResponse getReport() {
        return new ReportResponse(
                "Monthly API Usage Report",
                "READY",
                LocalDateTime.now().toString()
        );
    }

    @GetMapping("/health")
    public String health() {
        return "Backend service is running";
    }
}
