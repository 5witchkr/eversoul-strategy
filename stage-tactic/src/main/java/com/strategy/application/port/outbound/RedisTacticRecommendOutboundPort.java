package com.strategy.application.port.outbound;


import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisTacticRecommendOutboundPort {


    Set<String> getRecommendedIps(String key);

    void incrementOpsForValue(String key);

    void setExpire(String key, int time, TimeUnit days);

    void addOpsForSet(String key, String userIp);
}
