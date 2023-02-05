package com.strategy.application.port.inbound.portbatch;

import java.util.List;

public interface SoulConnectBatchInboundPort {

    void addData(List<Long> addedTacticIds);
}
