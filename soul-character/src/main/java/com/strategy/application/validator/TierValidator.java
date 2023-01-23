package com.strategy.application.validator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TierValidator {

    public void checkInputValue(String tier){
        List<String> useValue = List.of("SSS","SS","S","A","B","C","D","E");
        if (!useValue.contains(tier)) throw new IllegalArgumentException("input value exception");
    }
}
