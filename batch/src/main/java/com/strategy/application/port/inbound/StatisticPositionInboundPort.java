package com.strategy.application.port.inbound;

public interface StatisticPositionInboundPort {

    Long getAddedCount();

    void saveLastCount();
}
