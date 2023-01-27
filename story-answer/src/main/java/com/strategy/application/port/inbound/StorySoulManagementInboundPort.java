package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.StorySoulSaveDto;

public interface StorySoulManagementInboundPort {


    void postStorySoul(StorySoulSaveDto soulSaveDto);

    void deleteStorySoul(Long id);
}
