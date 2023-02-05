package com.strategy.application.port.inbound.portbatch;

import java.util.List;

public interface StatisticSoulConnectDataInboundPort {
    Long getAddedCount();

    void saveLastCount();

    List<Long> getAddedTacticId();
}
