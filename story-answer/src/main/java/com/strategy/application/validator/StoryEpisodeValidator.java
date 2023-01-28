package com.strategy.application.validator;


import com.strategy.enummodel.StoryEpisodeEnum;
import org.springframework.stereotype.Component;

@Component
public class StoryEpisodeValidator {


    public void checkEpisode(int value) {
        if (StoryEpisodeEnum.MIN_EPISODE.getValue() <= value
                && value <= StoryEpisodeEnum.MAX_EPISODE.getValue()){
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 에피소드 값");
    }
}
