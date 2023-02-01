package com.strategy.application.port.inbound.portbatch;

public interface StatisticSoulSelectInboundPort {

    Long getAddedCount();

    void saveLastCount();
}
