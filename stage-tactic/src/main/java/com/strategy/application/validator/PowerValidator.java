package com.strategy.application.validator;


import org.springframework.stereotype.Component;

@Component
public class PowerValidator {

    public void checkPower(int power){
        if (0 <= power && power <= 99999999 ){
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 전투력");
    }
}
