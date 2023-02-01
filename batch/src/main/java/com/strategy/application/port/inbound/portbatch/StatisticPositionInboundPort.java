package com.strategy.application.port.inbound.portbatch;

public interface StatisticPositionInboundPort {

    Long getAddedCount();

    void saveLastCount();
}
