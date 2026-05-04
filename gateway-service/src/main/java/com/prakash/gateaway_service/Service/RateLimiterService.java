package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.Entity.Plan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    public RateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isAllowed(String apiKey, Integer limit) {
        String key = "rate_limit:" + apiKey;

        Long currentCount = redisTemplate.opsForValue().increment(key);

        if (currentCount == null) {
            return false;
        }

        if (currentCount == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(60));
        }

        return currentCount <= limit;
    }
}