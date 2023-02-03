package com.strategy.application.facade;

import com.strategy.application.port.inbound.inputdto.StoryAnswerSaveDto;
import com.strategy.application.port.inbound.inputdto.StoryEpisodeSaveDto;
import com.strategy.application.port.inbound.inputdto.StoryQuestionSaveDto;
import com.strategy.application.port.inbound.inputdto.StorySoulSaveDto;

public interface StoryManagementPortFacade {

    void postSoul(StorySoulSaveDto soulSaveDto);

    void postEpisode(StoryEpisodeSaveDto storyEpisodeSaveDto);

    void postQuestion(StoryQuestionSaveDto storyQuestionSaveDto);

    void postAnswer(StoryAnswerSaveDto storyAnswerSaveDto);

    void deleteSoul(Long id);

    void deleteEpisode(Long id);

    void deleteQuestion(Long id);

    void deleteAnswer(Long id);
}
