package com.strategy.validator;

import com.strategy.application.validator.PowerValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("PowerValidator Tests")
public class PowerValidatorTests {

    private PowerValidator powerValidator;

    @TestFactory
    @DisplayName("checkPower Success Tests")
    Stream<DynamicTest> checkPowerSuccess() {
        powerValidator = new PowerValidator();
        final List<Integer> valueSource = List.of(1, 1000, 1000000);

        return valueSource.stream().map(value ->
                    DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                            () -> powerValidator.checkPower(value))
        );
    }

    @TestFactory
    @DisplayName("checkPower Fail Tests")
    Stream<DynamicTest> checkPowerFail() {
        powerValidator = new PowerValidator();
        final List<Integer> valueSource = List.of(-1, 99999999+1);

        return valueSource.stream().map(value ->
                DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> powerValidator.checkPower(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 전투력"))
        );
    }
}

