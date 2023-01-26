package com.strategy.adpater.inbound.presentation;


import com.strategy.application.facade.TacticCommentFacade;
import com.strategy.application.port.inbound.inputdto.TacticCommentRequestDto;
import com.strategy.application.port.inbound.outputdto.TacticCommentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tacticcomment")
public class TacticCommentApi {

    private final TacticCommentFacade tacticCommentFacade;

    public TacticCommentApi(TacticCommentFacade tacticCommentFacade) {
        this.tacticCommentFacade = tacticCommentFacade;
    }

    @GetMapping("/{tacticId}")
    public ResponseEntity<List<TacticCommentResponseDto>> getComment(@PathVariable Long tacticId) {
        return ResponseEntity.ok().body(tacticCommentFacade.getComments(tacticId));
    }

    @PostMapping("")
    public ResponseEntity<Void> postComment(@Validated @RequestBody TacticCommentRequestDto commentRequestDto) {
        tacticCommentFacade.postComment(commentRequestDto);
        return ResponseEntity.created(
                        URI.create("/api/tacticcomment/" + commentRequestDto.getTacticId()))
                .build();
    }
}
