package com.strategy.application.validator;


import org.springframework.stereotype.Component;

@Component
public class StorySoulIdValidator {

    public void checkSoulId(Long soulId) {
        if (0L < soulId && soulId < 32L){
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 정령정보");
    }
}
