package com.strategy.adpater.inbound.presentation;


import com.strategy.application.facade.TacticRecommendPortFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/tacticrecommend")
public class TacticRecommendApi {

    private final TacticRecommendPortFacade tacticRecommendPortFacade;

    public TacticRecommendApi(TacticRecommendPortFacade tacticRecommendPortFacade) {
        this.tacticRecommendPortFacade = tacticRecommendPortFacade;
    }


    @PostMapping("{tacticId}")
    public ResponseEntity<Void> postRecommend(@PathVariable Long tacticId,
                                              HttpServletRequest request) {
        String userIp = getUserIp(request);

        tacticRecommendPortFacade.postRecommend(tacticId, userIp);

        return ResponseEntity.created(
                        URI.create("/api/stagetactic/tactic" + tacticId))
                .build();
    }


    private String getUserIp(HttpServletRequest request) {
        String userIp = null;
        userIp = request.getHeader("X-Forwarded-For");

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getHeader("Proxy-Client-IP");
        }

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getHeader("WL-Proxy-Client-IP");
        }

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getHeader("HTTP_CLIENT_IP");
        }

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getHeader("X-Real-IP");
        }

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getHeader("X-RealIP");
        }

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getHeader("REMOTE_ADDR");
        }

        if (userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)) {
            userIp = request.getRemoteAddr();
        }
        return userIp;
    }
}
