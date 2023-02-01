package com.strategy.application.port.inbound;

public interface StatisticSoulConnectInboundPort {
    Long getAddedCount();

    void saveLastCount();
}
