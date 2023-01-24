package com.strategy.application.validator;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionValidator {

    public void checkPosition(String position){
        List<String> useValue = List.of("기본","수비","저격","돌격");
        if (!useValue.contains(position)) throw new IllegalArgumentException("유효하지 않은 포지션");
    }
}
