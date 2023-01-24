package com.strategy.validator;


import com.strategy.application.validator.StageParamValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("StageParamValidator Tests")
public class StageParamValidatorTests {

    private StageParamValidator stageParamValidator;

    @TestFactory
    @DisplayName("checkLocation Success Tests")
    Stream<DynamicTest> checkLocationSuccess() {
        stageParamValidator = new StageParamValidator();
        final List<Integer> valueSource = IntStream.rangeClosed(1,15)
                .boxed()
                .collect(Collectors.toList());

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> stageParamValidator.checkLocation(value)));
    }

    @TestFactory
    @DisplayName("checkLocation Fail Tests")
    Stream<DynamicTest> checkLocationFail() {
        stageParamValidator = new StageParamValidator();
        final List<Integer> source1 = IntStream.rangeClosed(16,30)
                .boxed()
                .collect(Collectors.toList());
        final List<Integer> source2 = List.of(0, -1, -10, 500);

        final List<Integer> valueSource = Stream.of(source1,source2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> stageParamValidator.checkLocation(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("존재하지 않는 스테이지")));
    }

    @TestFactory
    @DisplayName("checkStep Success Tests")
    Stream<DynamicTest> checkStepSuccess() {
        stageParamValidator = new StageParamValidator();
        final List<Integer> valueSource = IntStream.rangeClosed(1,45)
                .boxed()
                .collect(Collectors.toList());

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> stageParamValidator.checkStep(value)));
    }

    @TestFactory
    @DisplayName("checkStep Fail Tests")
    Stream<DynamicTest> checkStepFail() {
        stageParamValidator = new StageParamValidator();
        final List<Integer> source1 = IntStream.rangeClosed(51,60)
                .boxed()
                .collect(Collectors.toList());
        final List<Integer> source2 = List.of(0, 500, -1, -10);

        final List<Integer> valueSource = Stream.of(source1,source2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> stageParamValidator.checkStep(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("존재하지 않는 단계")));
    }
}
