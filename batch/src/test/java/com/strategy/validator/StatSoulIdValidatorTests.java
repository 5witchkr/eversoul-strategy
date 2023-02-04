package com.strategy.validator;


import com.strategy.application.validator.StatSoulIdValidator;
import com.strategy.enummodel.SoulIdEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("StatSoulIdValidator Tests")
public class StatSoulIdValidatorTests {

    private StatSoulIdValidator statSoulIdValidator;

    @TestFactory
    @DisplayName("check SoulId Success")
    Stream<DynamicTest> successCheckSoulId(){
        statSoulIdValidator = new StatSoulIdValidator();
        final List<Long> valueSource = LongStream
                .rangeClosed(
                        SoulIdEnum.SOUL_ID_START.getValue(),
                        SoulIdEnum.SOUL_ID_END.getValue())
                .boxed()
                .collect(Collectors.toList());

        return valueSource.stream().map(value ->
                DynamicTest.dynamicTest("성공케이스: 유효한 soulId",
                        () -> statSoulIdValidator.checkSoulId(value))
        );
    }

    @TestFactory
    @DisplayName("check SoulId Fail")
    Stream<DynamicTest> failCheckSoulId(){
        statSoulIdValidator = new StatSoulIdValidator();
        final List<Long> valueSource = LongStream
                .rangeClosed(
                        SoulIdEnum.SOUL_ID_END.getValue()+1,
                        SoulIdEnum.SOUL_ID_END.getValue()+10)
                .boxed()
                .collect(Collectors.toList());
        valueSource.add(-1L);
        valueSource.add(0L);
        valueSource.add(1314L);
        return valueSource.stream().map(value ->
                DynamicTest.dynamicTest("실패케이스: 유효하지 않은 soulId IllegalArgException 반환",
                        () -> assertThatThrownBy(
                                () -> statSoulIdValidator.checkSoulId(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 SoulId")
                )
        );
    }
}
