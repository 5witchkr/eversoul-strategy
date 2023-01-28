package com.strategy.application.validator;

import com.strategy.constantmodel.SoulNameConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BanSoulsValidator {

    public List<String> filterBanSouls(List<String> bans){
        return bans.stream()
                .filter(SoulNameConstants.allSoulNames::contains)
                .collect(Collectors.toList());
    }
}
