package com.strategy.validoator;

import com.strategy.application.validator.StorySoulIdValidator;
import com.strategy.enummodel.SoulIdEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;



@DisplayName("StorySoulIdValidator Tests")
public class StorySoulIdValidatorTests {
    private StorySoulIdValidator storySoulIdValidator;


    @TestFactory
    @DisplayName("checkSoulIdSuccess case")
    Stream<DynamicTest> checkSoulIdSuccess() {
        storySoulIdValidator = new StorySoulIdValidator();

        final List<Long> valueSource = LongStream
                .rangeClosed(
                        SoulIdEnum.SOUL_ID_START.getValue(), SoulIdEnum.SOUL_ID_END.getValue())
                .boxed()
                .collect(Collectors.toList());

        return valueSource.stream().map(value ->
                DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> storySoulIdValidator.checkSoulId(value)));
    }


    @TestFactory
    @DisplayName("checkSoulId Fail case")
    Stream<DynamicTest> checkSoulIdFail() {
        storySoulIdValidator = new StorySoulIdValidator();

        final List<Long> valueSource = List.of(-1L, 0L,
                SoulIdEnum.SOUL_ID_START.getValue() -1L,
                SoulIdEnum.SOUL_ID_END.getValue() +1L);

        return valueSource.stream().map( value ->
                DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> storySoulIdValidator.checkSoulId(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 정령정보")));
    }
}

