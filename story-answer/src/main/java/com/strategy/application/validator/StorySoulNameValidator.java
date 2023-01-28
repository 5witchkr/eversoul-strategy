package com.strategy.application.validator;


import com.strategy.constantmodel.SoulNameConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StorySoulNameValidator {
    public void checkSoulName(String value) {
        if (!SoulNameConstants.allSoulNames.contains(value)){
            throw new IllegalArgumentException("유효하지 않은 정령이름");
        }
    }
}
