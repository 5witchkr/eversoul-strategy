package com.strategy.application.port.inbound;

public interface StatisticSoulSelectInboundPort {

    Long getAddedCount();

    void saveLastCount();
}
