package com.strategy.adapter.inbound.presentation;


import com.strategy.application.facade.StatisticPortFacade;
import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stat")
public class StatisticApi {

    private final StatisticPortFacade statisticPortFacade;


    public StatisticApi(StatisticPortFacade statisticPortFacade) {
        this.statisticPortFacade = statisticPortFacade;
    }


    @GetMapping("/soulselect")
    public ResponseEntity<List<SoulSelectResponseDto>> getTopSoulSelect(@RequestParam int argNumber) {
        return ResponseEntity.ok(statisticPortFacade.getTopSoulSelect(argNumber));
    }

    @GetMapping("/soulselect/{soulId}")
    public ResponseEntity<SoulSelectResponseDto> getOneSoulSelect(@PathVariable Long soulId) {
        return ResponseEntity.ok(statisticPortFacade.getOneSoulSelect(soulId));
    }

    @GetMapping("/position")
    public ResponseEntity<List<PositionStatResponseDto>> getPositionStat() {
        return ResponseEntity.ok(statisticPortFacade.getPositionStat());
    }
}
