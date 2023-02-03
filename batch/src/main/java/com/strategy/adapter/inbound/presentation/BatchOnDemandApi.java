package com.strategy.adapter.inbound.presentation;


import com.strategy.application.facade.BatchPortFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/batch-admin")
public class BatchOnDemandApi {

    @Value("${devValue}")
    private String value;

    private final BatchPortFacade batchPortFacade;

    public BatchOnDemandApi(BatchPortFacade batchPortFacade) {
        this.batchPortFacade = batchPortFacade;
    }


    @PostMapping("/soulselect")
    public String postSoulSelect(@RequestParam String devValue) {

        if (!devValue.equals(value)) return "X";

        batchPortFacade.soulSelectBatch();

        return "O";
    }

    @PostMapping("/position")
    public String postPosition(@RequestParam String devValue) {

        if (!devValue.equals(value)) return "X";

        batchPortFacade.positionBatch();

        return "O";
    }

    @PostMapping("/soulconnect")
    public String postSoulConnect(@RequestParam String devValue) {

        if (!devValue.equals(value)) return "X";

        batchPortFacade.soulConnectBatch();

        return "O";
    }
}
