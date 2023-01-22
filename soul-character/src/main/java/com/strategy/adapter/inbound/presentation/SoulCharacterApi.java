package com.strategy.adapter.inbound.presentation;


import com.strategy.application.port.inbound.outputdto.TierDto;
import com.strategy.application.port.inbound.SoulCharacterInboundPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/soulcharacter")
public class SoulCharacterApi {

    private final SoulCharacterInboundPort soulCharacterInboundPort;

    public SoulCharacterApi(SoulCharacterInboundPort soulCharacterInboundPort) {
        this.soulCharacterInboundPort = soulCharacterInboundPort;
    }

    @GetMapping("/tier")
    public ResponseEntity<TierDto> getSoulTier(@RequestParam Long tier) {
        return new ResponseEntity<>(soulCharacterInboundPort.getTier(tier), HttpStatus.OK);
    }
}
