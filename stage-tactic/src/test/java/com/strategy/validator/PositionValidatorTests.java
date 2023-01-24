package com.strategy.validator;

import com.strategy.application.validator.PositionValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("PositionValidator Tests")
public class PositionValidatorTests {

    private PositionValidator positionValidator;

    @TestFactory
    @DisplayName("checkPosition Success Tests")
    Stream<DynamicTest> checkPositionSuccess() {
        positionValidator = new PositionValidator();
        final String position1 = "기본";
        final String position2 = "수비";
        final String position3 = "저격";
        final String position4 = "돌격";

        return Stream.of(position1, position2, position3, position4).map(value ->
            DynamicTest.dynamicTest("성공케이스: validator를 통과한다..", () ->
                positionValidator.checkPosition(value))
        );
    }
    @TestFactory
    @DisplayName("checkPosition Fail Tests")
    Stream<DynamicTest> checkPositionFail() {
        positionValidator = new PositionValidator();
        final String position1 = "";
        final String position2 = " ";
        final String position3 = "기";
        final String position4 = "기본형";

        return Stream.of(position1, position2, position3, position4).map(value ->
                DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> positionValidator.checkPosition(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 포지션"))
        );
    }
}

