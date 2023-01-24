package com.strategy.application.validator;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StageParamValidator {

    public void checkLocation(int location){
        List<Integer> useValue = IntStream.rangeClosed(1,15)
                .boxed().collect(Collectors.toList());
        if (!useValue.contains(location)) throw new IllegalArgumentException("존재하지 않는 스테이지");
    }

    public void checkStep(int step){
        List<Integer> useValue = IntStream.rangeClosed(1,45)
                .boxed().collect(Collectors.toList());
        if (!useValue.contains(step)) throw new IllegalArgumentException("존재하지 않는 단계");
    }
}
