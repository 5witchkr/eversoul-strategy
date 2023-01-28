package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticValidReqDto;
import com.strategy.application.port.inbound.inputdto.TacticRequestDto;

import java.util.List;

public interface PostTacticInboundPort {

    void postTactic(TacticRequestDto tacticRequestDto,
                    List<SoulCharacterTacticValidReqDto> soulCharacterTacticValidReqDtos);
}
