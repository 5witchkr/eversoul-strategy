package com.strategy.application.validator;


import com.strategy.enummodel.StoryEndingEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class StoryEndingValidoatr {

    public void checkEndingValue(String value) {
        if (Arrays.stream(StoryEndingEnum.values())
                .noneMatch(v -> Objects.equals(v.getValue(), value))){
            throw new IllegalArgumentException("잘못된 엔딩값");
        }
    }
}
