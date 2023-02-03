package com.strategy.adapter.inbound.presentation;


import com.strategy.application.facade.SoulCharacterPortFacade;
import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/soulcharacter")
public class SoulCharacterApi {

    private final SoulCharacterPortFacade soulCharacterPortFacade;

    public SoulCharacterApi(SoulCharacterPortFacade soulCharacterPortFacade) {
        this.soulCharacterPortFacade = soulCharacterPortFacade;
    }


    @GetMapping("/tier")
    public ResponseEntity<List<SoulCharacterResponseDto>> getSoulByTier(@RequestParam String tier) {
        return ResponseEntity.ok(soulCharacterPortFacade.getSoulByTier(tier));
    }
}
