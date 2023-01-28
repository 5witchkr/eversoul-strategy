package com.strategy.application.validator;


import com.strategy.enummodel.TacticPositionEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class PositionValidator {

    public void checkPosition(String position){
        if (Arrays.stream(TacticPositionEnum.values())
                .anyMatch(value -> Objects.equals(value.getValue(), position))){
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 포지션");
    }
}
