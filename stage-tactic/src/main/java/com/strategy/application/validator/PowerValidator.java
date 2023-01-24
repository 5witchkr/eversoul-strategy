package com.strategy.application.validator;


import org.springframework.stereotype.Component;

@Component
public class PowerValidator {

    public void checkPower(int power){
        if (power < 1 || power > 99999999){
            throw new IllegalArgumentException("유효하지 않은 전투력");
        }
    }
}
