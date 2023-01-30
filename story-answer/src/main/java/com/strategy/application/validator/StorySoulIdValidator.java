package com.strategy.application.validator;


import com.strategy.enummodel.SoulIdEnum;
import org.springframework.stereotype.Component;

@Component
public class StorySoulIdValidator {

    public void checkSoulId(Long soulId) {
        if (SoulIdEnum.SOUL_ID_START.getValue()
                <= soulId && soulId <=
                SoulIdEnum.SOUL_ID_END.getValue()){
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 정령정보");
    }
}
