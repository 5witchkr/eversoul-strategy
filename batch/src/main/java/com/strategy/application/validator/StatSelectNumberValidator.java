package com.strategy.application.validator;


import com.strategy.enummodel.SoulIdEnum;
import org.springframework.stereotype.Component;

@Component
public class StatSelectNumberValidator {

    public void checkSelectNumber(int argNumber){
        if (SoulIdEnum.SOUL_ID_START.getValue() -1 <= argNumber
                && argNumber <= SoulIdEnum.SOUL_ID_END.getValue()) {
            return;
        }
        //todo refactor IllegalStateException && IllegalArgumentException
        throw new IllegalArgumentException("유효하지 않은 argNumber");
    }
}
