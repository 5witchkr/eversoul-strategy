package com.strategy.application.validator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BanSoulsValidator {

    public List<String> filterBanSouls(List<String> bans){
        List<String> allSoulCharacter = List.of(
          "아드리안","아야메","메피","클레르","제이드","린지","캐서린",
                "셰리", "도라", "지호", "순이", "아이라", "시하", "미카", "플린",
                "하루", "클라라", "르네", "탈리아", "니콜", "비비안", "미리암",
                "클로이", "나이아", "비올레트", "레베카", "프림", "재클린", "에루샤",
                "페트라", "니니", "리타", "로제", "샤링", "루리", "알리샤", "르웨인",
                "무명", "카렌", "아이린", "캐니", "픽시", "캐스퍼", "홍란"
        );
        return bans.stream()
                .filter(allSoulCharacter::contains)
                .collect(Collectors.toList());
    }
}
