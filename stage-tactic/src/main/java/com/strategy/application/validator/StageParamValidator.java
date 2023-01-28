package com.strategy.application.validator;


import com.strategy.enummodel.StageValueEnum;
import com.strategy.enummodel.StoryEpisodeEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StageParamValidator {

    public void checkLocation(int location){
        if (StageValueEnum.LOCATION_MIN.getValue() <= location
                && location <= StageValueEnum.LOCATION_MAX.getValue()){
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 스테이지");
    }

    public void checkStep(int step){
        if (StageValueEnum.STEP_MIN.getValue() <= step
                && step <= StageValueEnum.STEP_MAX.getValue()){
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 단계");
    }
}
