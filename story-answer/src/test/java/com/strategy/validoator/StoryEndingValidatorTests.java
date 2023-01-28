package com.strategy.validoator;

import com.strategy.application.validator.StoryEndingValidoatr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("StoryEndingValidator tests")
public class StoryEndingValidatorTests {
    @TestFactory
    @DisplayName("checkEndingValue Success Tests")
    Stream<DynamicTest> checkEndingValueSuccess() {
        StoryEndingValidoatr storyEndingValidoatr = new StoryEndingValidoatr();
        final List<String> valueSource = List.of("TRUE", "BAD");

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> storyEndingValidoatr.checkEndingValue(value)));
    }

    @TestFactory
    @DisplayName("checkEndingValue Fail Tests")
    Stream<DynamicTest> checkEndingValueFail() {
        StoryEndingValidoatr storyEndingValidoatr = new StoryEndingValidoatr();
        final List<String> valueSource = List.of(" ", "", "True", "Bad", "#@#", "1");

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> storyEndingValidoatr.checkEndingValue(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("잘못된 엔딩값")));
    }
}
