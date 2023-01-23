package com.strategy.validator;

import com.strategy.application.validator.TierValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TierValidator Tests")
public class TierValidatorTests {
    private TierValidator tierValidator;

    @TestFactory
    @DisplayName("TierValidator checkInputValue Tests")
    Stream<DynamicTest> checkInputValueSuccess() {
        tierValidator = new TierValidator();
        final List<String> valueSource = List.of("SSS","SS","S","A","B","C","D","E");

        return valueSource.stream()
                .map( value -> DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> tierValidator.checkInputValue(value)));
    }

    @TestFactory
    @DisplayName("TierValidator checkInputValue Tests")
    Stream<DynamicTest> checkInputValueFail() {
        tierValidator = new TierValidator();
        final List<String> valueSource = List.of("F", "3", "SSSS", "1", "2", "4", "sss", "s");

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                    assertThatThrownBy(() -> tierValidator.checkInputValue(value))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("input value exception")
                        )
                );
    }
}
