package com.strategy.application.port.inbound.portbatch;

public interface StatisticSoulConnectInboundPort {
    Long getAddedCount();

    void saveLastCount();
}
