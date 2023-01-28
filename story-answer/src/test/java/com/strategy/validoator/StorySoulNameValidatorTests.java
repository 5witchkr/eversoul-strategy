package com.strategy.validoator;

import com.strategy.application.validator.StorySoulNameValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StorySoulNameValidatorTests {
    private StorySoulNameValidator storySoulNameValidator;

    @TestFactory
    @DisplayName("checkLevelByDtos Success Tests")
    Stream<DynamicTest> checkSoulNameByDtosSuccess() {
        storySoulNameValidator = new StorySoulNameValidator();
        final List<String> valueSource = List.of("메피", "아드리안", "지호","탈리아");

        return valueSource.stream().map(value ->
                DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> storySoulNameValidator.checkSoulName(value)
                )
        );
    }

    @TestFactory
    @DisplayName("checkLevelByDtos Fail Tests")
    Stream<DynamicTest> checkSoulNameByDtosFail() {
        storySoulNameValidator = new StorySoulNameValidator();
        final List<String> valueSource = List.of("메", "아드리안안", " ","");

        return valueSource.stream().map(value ->
                DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> storySoulNameValidator.checkSoulName(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 정령이름"))
        );
    }
}


