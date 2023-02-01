package com.strategy.adapter.inbound.presentation;


import com.strategy.application.facade.BatchFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/batch-admin")
public class BatchOnDemandApi {

    @Value("${devValue}")
    private String value;

    private final BatchFacade batchFacade;


    public BatchOnDemandApi(BatchFacade batchFacade) {
        this.batchFacade = batchFacade;
    }

    @PostMapping("/soulselect")
    public String postSoulSelect(@RequestParam String devValue) {

        if (!devValue.equals(value)) return "X";

        batchFacade.soulSelectBatch();

        return "O";
    }

    @PostMapping("/position")
    public String postPosition(@RequestParam String devValue) {

        if (!devValue.equals(value)) return "X";

        batchFacade.positionBatch();

        return "O";
    }

    @PostMapping("/soulconnect")
    public String postSoulConnect(@RequestParam String devValue) {

        if (!devValue.equals(value)) return "X";

        batchFacade.soulConnectBatch();

        return "O";
    }
}
