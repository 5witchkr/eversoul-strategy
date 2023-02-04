package com.strategy.application.validator;


import com.strategy.enummodel.SoulIdEnum;
import org.springframework.stereotype.Component;

@Component
public class StatSoulIdValidator {


    public void checkSoulId(Long id){
        if (SoulIdEnum.SOUL_ID_START.getValue() <= id
                && id <= SoulIdEnum.SOUL_ID_END.getValue()) {
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 SoulId");
    }
}
