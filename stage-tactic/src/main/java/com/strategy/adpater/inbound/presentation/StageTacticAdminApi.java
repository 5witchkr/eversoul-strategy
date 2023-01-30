package com.strategy.adpater.inbound.presentation;

import com.strategy.application.facade.StageTacticFacade;
import com.strategy.application.facade.StageTacticManagementFacade;
import com.strategy.application.port.inbound.inputdto.souldto.SoulPutDto;
import com.strategy.application.port.inbound.inputdto.souldto.SoulSaveDto;
import com.strategy.application.port.inbound.inputdto.stagedto.StagePutDto;
import com.strategy.application.port.inbound.inputdto.stagedto.StageSaveDto;
import com.strategy.application.port.inbound.inputdto.tacticdto.TacticPutDto;
import com.strategy.application.port.inbound.inputdto.tacticdto.TacticRequestDto;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/stagetactic-admin")
public class StageTacticAdminApi {

    @Value("${devValue}")
    private String value;

    private final StageTacticFacade stageTacticFacade;
    private final StageTacticManagementFacade stageTacticManagementFacade;

    public StageTacticAdminApi(StageTacticFacade stageTacticFacade, StageTacticManagementFacade stageTacticManagementFacade) {
        this.stageTacticFacade = stageTacticFacade;
        this.stageTacticManagementFacade = stageTacticManagementFacade;
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
    public void postSoul(@RequestParam String devValue,
                         @RequestBody SoulSaveDto soulSaveDto){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.saveSoul(soulSaveDto);
    }

    @PostMapping("/stage")
    public void postStage(@RequestParam String devValue,
                          @RequestBody StageSaveDto stageSaveDto){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.saveStage(stageSaveDto);
    }

    @DeleteMapping("/tactic")
    public void deleteTactic(@RequestParam String devValue,
                             @RequestParam Long id){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.deleteTactic(id);
    }

    @DeleteMapping("/stage")
    public void deleteStage(@RequestParam String devValue,
                            @RequestParam Long id){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.deleteStage(id);
    }

    @DeleteMapping("/soul")
    public void deleteSoul(@RequestParam String devValue,
                           @RequestParam Long id){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.deleteSoul(id);
    }

    @PutMapping("/tactic")
    public void putTactic(@RequestParam String devValue,
                          @RequestBody TacticPutDto tacticPutDto){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.putTactic(tacticPutDto);
    }

    @PutMapping("/stage")
    public void putStage(@RequestParam String devValue,
                         @RequestBody StagePutDto stagePutDto){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.putStage(stagePutDto);
    }

    @PutMapping("/soul")
    public void putSoul(@RequestParam String devValue,
                        @RequestBody SoulPutDto soulPutDto){
        if (!devValue.equals(value)) return;
        stageTacticManagementFacade.putSoul(soulPutDto);
    }
}
