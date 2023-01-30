package com.strategy.adpater.inbound.presentation;


import com.strategy.application.facade.StageTacticFacade;
import com.strategy.application.port.inbound.inputdto.tacticdto.TacticRequestDto;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/stagetactic")
public class StageTacticApi {

    private final StageTacticFacade stageTacticFacade;

    public StageTacticApi(StageTacticFacade stageTacticFacade) {
        this.stageTacticFacade = stageTacticFacade;
    }

    @GetMapping("/{location}/{step}")
    public ResponseEntity<List<RecommendTacticResponseDto>> recommendTactic(@PathVariable int location,
                                                                            @PathVariable int step,
                                                                            @RequestParam List<String> bans) {
        return ResponseEntity.ok().body(stageTacticFacade.getRecommendWithoutBans(location, step, bans));
    }


    @PostMapping("/tactic")
    public ResponseEntity<Void> postTactic(@Validated @RequestBody TacticRequestDto tacticRequestDto) {
        stageTacticFacade.postTactic(tacticRequestDto);
        return ResponseEntity.created(
                URI.create("/api/stagetactic/" + tacticRequestDto.getLocation() + "/" + tacticRequestDto.getStep()))
                .build();
    }
}
