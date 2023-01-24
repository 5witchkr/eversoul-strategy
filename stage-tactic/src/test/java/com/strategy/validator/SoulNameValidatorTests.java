package com.strategy.validator;

import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticRequestDto;
import com.strategy.application.validator.SoulNameValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("SoulNameValidator Tests")
public class SoulNameValidatorTests {

    private SoulNameValidator soulNameValidator;

    @TestFactory
    @DisplayName("checkLevelByDtos Success Tests")
    Stream<DynamicTest> checkSoulNameByDtosSuccess() {
        soulNameValidator = new SoulNameValidator();
        final SoulCharacterTacticRequestDto dto1 = SoulCharacterTacticRequestDto.builder().name("메피").level(101).build();
        final SoulCharacterTacticRequestDto dto2 = SoulCharacterTacticRequestDto.builder().name("미리암").level(181).build();
        final SoulCharacterTacticRequestDto dto3 = SoulCharacterTacticRequestDto.builder().name("무명").level(1).build();
        final List<SoulCharacterTacticRequestDto> value = List.of(dto1, dto2, dto3);

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> soulNameValidator.checkSoulNameByDtos(value)
                )
        );
    }

    @TestFactory
    @DisplayName("checkLevelByDtos Fail Tests")
    Stream<DynamicTest> checkSoulNameByDtosFail() {
        soulNameValidator = new SoulNameValidator();
        final SoulCharacterTacticRequestDto dto1 = SoulCharacterTacticRequestDto.builder().name("메").level(100).build();
        final SoulCharacterTacticRequestDto dto2 = SoulCharacterTacticRequestDto.builder().name("미리암암").level(175).build();
        final SoulCharacterTacticRequestDto dto3 = SoulCharacterTacticRequestDto.builder().name(" ").level(10).build();
        final SoulCharacterTacticRequestDto dto4 = SoulCharacterTacticRequestDto.builder().name("").level(10).build();
        final List<SoulCharacterTacticRequestDto> value1 = List.of(dto1);
        final List<SoulCharacterTacticRequestDto> value2 = List.of(dto2);
        final List<SoulCharacterTacticRequestDto> value3 = List.of(dto3);
        final List<SoulCharacterTacticRequestDto> value4 = List.of(dto4);

        return Stream.of(value1, value2, value3, value4).map(value ->
                DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> soulNameValidator.checkSoulNameByDtos(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 정령이름"))
        );
    }
}

