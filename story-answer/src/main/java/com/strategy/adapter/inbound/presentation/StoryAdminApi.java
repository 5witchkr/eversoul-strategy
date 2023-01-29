package com.strategy.adapter.inbound.presentation;

import com.strategy.application.facade.StoryEndingFacade;
import com.strategy.application.facade.StoryManagementFacade;
import com.strategy.application.port.inbound.inputdto.StoryAnswerSaveDto;
import com.strategy.application.port.inbound.inputdto.StoryEpisodeSaveDto;
import com.strategy.application.port.inbound.inputdto.StoryQuestionSaveDto;
import com.strategy.application.port.inbound.inputdto.StorySoulSaveDto;
import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/story-admin")
public class StoryAdminApi {

    @Value("${devValue}")
    private String value;

    private final StoryEndingFacade storyEndingFacade;
    private final StoryManagementFacade storyManagementFacade;

    public StoryAdminApi(StoryEndingFacade storyEndingFacade, StoryManagementFacade storyManagementFacade) {
        this.storyEndingFacade = storyEndingFacade;
        this.storyManagementFacade = storyManagementFacade;
    }


    @GetMapping("/{soulId}")
    public ResponseEntity<List<StoryQuestionEndingResponseDto>> recommendTactic(@PathVariable Long soulId,
                                                                                @RequestParam int episode,
                                                                                @RequestParam String ending) {
        return ResponseEntity.ok().body(storyEndingFacade.getSoulEndingByEpisode(soulId,episode,ending));
    }


    @PostMapping("/soul")
    public void postStorySoul(@RequestParam String devValue,
                              @RequestBody StorySoulSaveDto soulSaveDto){
        if (!devValue.equals(value)) return;
        storyManagementFacade.postSoul(soulSaveDto);
    }
    @PostMapping("/episode")
    public void postStoryEpisode(@RequestParam String devValue,
                              @RequestBody StoryEpisodeSaveDto storyEpisodeSaveDto){
        if (!devValue.equals(value)) return;
        storyManagementFacade.postEpisode(storyEpisodeSaveDto);
    }
    @PostMapping("/question")
    public void postStoryQuestion(@RequestParam String devValue,
                              @RequestBody StoryQuestionSaveDto storyQuestionSaveDto){
        if (!devValue.equals(value)) return;
        storyManagementFacade.postQuestion(storyQuestionSaveDto);
    }
    @PostMapping("/answer")
    public void postStoryAnswer(@RequestParam String devValue,
                              @RequestBody StoryAnswerSaveDto storyAnswerSaveDto){
        if (!devValue.equals(value)) return;
        storyManagementFacade.postAnswer(storyAnswerSaveDto);
    }
}
