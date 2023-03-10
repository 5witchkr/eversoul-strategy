package com.strategy.application.validator;


import com.strategy.application.port.inbound.inputdto.souldto.SoulCharacterTacticValidReqDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelValidator {

    public void checkLevel(int level){}

    public void checkLevelByDtos(List<SoulCharacterTacticValidReqDto> soulCharacters) {
        long failValueCount = soulCharacters.stream()
                .filter(value -> 0 > value.getLevel() || value.getLevel() > 2000)
                .count();
        if (failValueCount > 0) throw new IllegalArgumentException("유효하지 않은 레벨");
    }
}
