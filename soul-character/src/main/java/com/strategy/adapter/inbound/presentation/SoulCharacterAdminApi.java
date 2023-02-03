package com.strategy.adapter.inbound.presentation;


import com.strategy.application.facade.SoulCharacterPortFacade;
import com.strategy.application.facade.SoulManagementPortFacade;
import com.strategy.application.port.inbound.inputdto.SoulPutRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulSaveRequestDto;
import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soulcharacter-admin")
public class SoulCharacterAdminApi {

    @Value("${devValue}")
    private String value;


    private final SoulManagementPortFacade soulManagementPortFacade;
    private final SoulCharacterPortFacade soulCharacterPortFacade;

    public SoulCharacterAdminApi(SoulManagementPortFacade soulManagementPortFacade, SoulCharacterPortFacade soulCharacterPortFacade) {
        this.soulManagementPortFacade = soulManagementPortFacade;
        this.soulCharacterPortFacade = soulCharacterPortFacade;
    }

    @GetMapping("/tier")
    public ResponseEntity<List<SoulCharacterResponseDto>> getSoulByTier(@RequestParam String tier) {
        return ResponseEntity.ok(soulCharacterPortFacade.getSoulByTier(tier));
    }

    @PostMapping("/soul")
    public void postSoul(@RequestParam String devValue,
                         @RequestBody SoulSaveRequestDto soulSaveRequestDto){
        if (!devValue.equals(value)) return;
        soulManagementPortFacade.saveSoul(soulSaveRequestDto);
    }

    @DeleteMapping("/soul")
    public void deleteSoul(@RequestParam String devValue, Long id){
        if (!devValue.equals(value)) return;
        soulManagementPortFacade.deleteSoul(id);
    }

    @PutMapping("/soul")
    public void putSoul(@RequestParam String devValue,
                        @RequestBody SoulPutRequestDto soulPutRequestDto){
        if (!devValue.equals(value)) return;
        soulManagementPortFacade.putSoul(soulPutRequestDto);
    }
}
