package com.strategy.application.validator;


import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelValidator {

    public void checkLevel(int level){}

    public void checkLevelByDtos(List<SoulCharacterTacticRequestDto> soulCharacters) {
        long failValueCount = soulCharacters.stream()
                .filter(value -> value.getLevel() < 1 || value.getLevel() > 1000)
                .count();
        if (failValueCount > 0) throw new IllegalArgumentException("유효하지 않은 레벨");
    }
}
