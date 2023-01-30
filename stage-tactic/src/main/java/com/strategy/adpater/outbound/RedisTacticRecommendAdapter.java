package com.strategy.adpater.outbound;


import com.strategy.application.port.outbound.RedisTacticRecommendOutboundPort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class RedisTacticRecommendAdapter implements RedisTacticRecommendOutboundPort {

    private final StringRedisTemplate redisTemplate;

    public RedisTacticRecommendAdapter(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addOpsForSet(String key, String userIp) {
        redisTemplate.opsForSet().add(key, userIp);
    }

    @Override
    public Set<String> getRecommendedIps(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void incrementOpsForValue(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    @Override
    public void setExpire(String key, int value, TimeUnit days) {
        redisTemplate.expire(key, 1, days);
    }

}
