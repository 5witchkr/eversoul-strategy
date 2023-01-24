package com.strategy.validator;

import com.strategy.application.validator.BanSoulsValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BanSoulValidator Tests")
public class BanSoulNameValidatorTests {
    private BanSoulsValidator banSoulsValidator;

    @TestFactory
    @DisplayName("filterBanSouls Success Tests")
    Stream<DynamicTest> filterBanSoulsSuccess1() {
        banSoulsValidator = new BanSoulsValidator();
        final List<String> valueSource = List.of(" ", "", "아드리안","나이아", "12312", "도라","+", "#$@#$", "아");
        final List<String> filteredValue = List.of("아드리안","나이아","도라");

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 필터링된 결과를 반환한다.", () -> {

                    List<String> result = banSoulsValidator.filterBanSouls(valueSource);

                    assertThat(result.size()).isEqualTo(3);
                    assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(filteredValue);
                })
        );
    }
    @TestFactory
    @DisplayName("filterBanSouls Success Tests 2")
    Stream<DynamicTest> filterBanSoulsSuccess2() {
        banSoulsValidator = new BanSoulsValidator();
        final List<String> source1 = List.of("미리암", "메피", "아드리안");
        final List<String> source2 = List.of("나이아", "순이", "도라");
        final List<String> source3 = List.of("아야메", "니니", "지호");

        return Stream.of(source1,source2,source3)
                .map(value -> DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> banSoulsValidator.filterBanSouls(value)));
    }
}
