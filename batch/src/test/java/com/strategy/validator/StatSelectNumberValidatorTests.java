package com.strategy.validator;


import com.strategy.application.validator.StatSelectNumberValidator;
import com.strategy.enummodel.SoulIdEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("StatSelectNumberValidator Tests")
public class StatSelectNumberValidatorTests {

    private StatSelectNumberValidator statSelectNumberValidator;


    @TestFactory
    @DisplayName("checkSelectNumber success case")
    Stream<DynamicTest> checkSelectNumberSuccessCase() {
        statSelectNumberValidator = new StatSelectNumberValidator();

        List<Integer> valueSource = LongStream.range(
                SoulIdEnum.SOUL_ID_START.getValue()-1,
                SoulIdEnum.SOUL_ID_END.getValue())
                .mapToInt(Math::toIntExact)
                .boxed()
                .collect(Collectors.toList());

        return valueSource.stream().map(value -> DynamicTest.dynamicTest("성공케이스: 유효한 selectNumber",
                () -> statSelectNumberValidator.checkSelectNumber(value))
        );
    }

    @TestFactory
    @DisplayName("checkSelectNumber fail case")
    Stream<DynamicTest> checkSelectNumberFailCase() {
        statSelectNumberValidator = new StatSelectNumberValidator();

        List<Integer> valueSource = LongStream.range(
                        SoulIdEnum.SOUL_ID_END.getValue()+1,
                        SoulIdEnum.SOUL_ID_END.getValue()+10)
                .mapToInt(Math::toIntExact)
                .boxed()
                .collect(Collectors.toList());
        valueSource.add(-1);
        valueSource.add(100);

        return valueSource.stream().map(value -> DynamicTest.dynamicTest("실패케이스: 유효하지 않은 selectNumber",
                () -> assertThatThrownBy(
                        () -> statSelectNumberValidator.checkSelectNumber(value))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("유효하지 않은 argNumber")
                )
        );
    }
}
