package com.strategy.adapter.inbound.presentation;


import com.strategy.application.facade.StoryEndingFacade;
import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryApi {

    private final StoryEndingFacade storyEndingFacade;

    public StoryApi(StoryEndingFacade storyEndingFacade) {
        this.storyEndingFacade = storyEndingFacade;
    }


    @GetMapping("/{soulId}")
    public ResponseEntity<List<StoryQuestionEndingResponseDto>> recommendTactic(@PathVariable Long soulId,
                                                                                @RequestParam int episode,
                                                                                @RequestParam String ending) {
        return ResponseEntity.ok().body(storyEndingFacade.getSoulEndingByEpisode(soulId,episode,ending));
    }
}
