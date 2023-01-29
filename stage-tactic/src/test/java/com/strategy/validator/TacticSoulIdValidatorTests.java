package com.strategy.validator;


import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticValidReqDto;
import com.strategy.application.validator.TacticSoulIdValidator;
import com.strategy.constantmodel.SoulIdEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("TacticSoulIdValidator Tests")
public class TacticSoulIdValidatorTests {

    private TacticSoulIdValidator tacticSoulIdValidator;


    @TestFactory
    @DisplayName("checkSoulIdSuccess case")
    Stream<DynamicTest> checkSoulIdSuccess() {
        tacticSoulIdValidator = new TacticSoulIdValidator();
        final List<SoulCharacterTacticValidReqDto> valueSource1 = LongStream
                .rangeClosed(
                        SoulIdEnum.SOUL_ID_START.getValue(),
                        SoulIdEnum.SOUL_ID_END.getValue())
                .mapToObj(value -> SoulCharacterTacticValidReqDto.builder().soulId(value).build())
                .collect(Collectors.toList());

        final List<SoulCharacterTacticValidReqDto> valueSource2 = LongStream
                .rangeClosed(SoulIdEnum.SOUL_ID_START.getValue(), 15)
                .mapToObj(value -> SoulCharacterTacticValidReqDto.builder().soulId(value).build())
                .collect(Collectors.toList());

        final List<SoulCharacterTacticValidReqDto> valueSource3 = LongStream
                .rangeClosed(16,SoulIdEnum.SOUL_ID_END.getValue())
                .mapToObj(value -> SoulCharacterTacticValidReqDto.builder().soulId(value).build())
                .collect(Collectors.toList());

        return Stream.of(valueSource1,valueSource2,valueSource3).map(value ->
                DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> tacticSoulIdValidator.checkSoulId(value)));
    }


    @TestFactory
    @DisplayName("checkSoulId Fail case")
    Stream<DynamicTest> checkSoulIdFail() {
        tacticSoulIdValidator = new TacticSoulIdValidator();
        final List<SoulCharacterTacticValidReqDto> valueSource1 = List.of(
                SoulCharacterTacticValidReqDto.builder().soulId(0L).build());
        final List<SoulCharacterTacticValidReqDto> valueSource2 = List.of(
                SoulCharacterTacticValidReqDto.builder().soulId(SoulIdEnum.SOUL_ID_START.getValue()-1L).build());
        final List<SoulCharacterTacticValidReqDto> valueSource3 = List.of(
                SoulCharacterTacticValidReqDto.builder().soulId(SoulIdEnum.SOUL_ID_END.getValue()+1L).build());


        return Stream.of(valueSource1,valueSource2).map(value ->
                DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> tacticSoulIdValidator.checkSoulId(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 정령정보")));
    }

    @TestFactory
    @DisplayName("checkSoulId Fail case2")
    Stream<DynamicTest> checkSoulIdFail2() {
        tacticSoulIdValidator = new TacticSoulIdValidator();
        final List<SoulCharacterTacticValidReqDto> valueSource = List.of(
                SoulCharacterTacticValidReqDto.builder().build());

        return Stream.of(
                DynamicTest.dynamicTest("실패케이스: NullPointerException 을 반환한다.", () ->
                        assertThatThrownBy(() -> tacticSoulIdValidator.checkSoulId(valueSource))
                                .isInstanceOf(NullPointerException.class)
                ));
    }
}
