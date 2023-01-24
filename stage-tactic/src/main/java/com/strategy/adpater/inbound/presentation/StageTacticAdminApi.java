package com.strategy.adpater.inbound.presentation;

import com.strategy.application.facade.StageTacticFacade;
import com.strategy.application.port.inbound.inputdto.TacticRequestDto;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/stagetactic/admin")
public class StageTacticAdminApi {

    @Value("${devValue}")
    private String value;

    private final StageTacticFacade stageTacticFacade;

    public StageTacticAdminApi(StageTacticFacade stageTacticFacade) {
        this.stageTacticFacade = stageTacticFacade;
    }

    @GetMapping("/{location}/{step}")
    public ResponseEntity<List<RecommendTacticResponseDto>> recommendTactic(@PathVariable int location,
                                                                            @PathVariable int step,
                                                                            @RequestParam List<String> bans) {
        return ResponseEntity.ok().body(stageTacticFacade.getRecommendWithoutBans(location, step, bans));
    }

    @PostMapping("/tactic")
    public ResponseEntity<Void> postTactic(@RequestBody TacticRequestDto tacticRequestDto) {
        stageTacticFacade.postTactic(tacticRequestDto);
        return ResponseEntity.created(
                        URI.create("/api/stagetactic/" + tacticRequestDto.getLocation() + "/" + tacticRequestDto.getStep()))
                .build();
    }

    @PostMapping("/soul")
    public void postSoul(){

    }

    @PostMapping("/stage")
    public void postStage(){

    }

    @DeleteMapping("/tactic")
    public void deleteTactic(){

    }

    @DeleteMapping("/stage")
    public void deleteStage(){

    }

    @DeleteMapping("/soul")
    public void deleteSoul(){

    }

    @PutMapping("/tactic")
    public void putTactic(){

    }

    @PutMapping("/stage")
    public void putStage(){

    }

    @PutMapping("/soul")
    public void putSoul(){

    }


}
