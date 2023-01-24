package com.strategy.validator;

import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticRequestDto;
import com.strategy.application.validator.LevelValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LevelValidator Tests")
public class LevelValidatorTests {

    private LevelValidator levelValidator;

    @TestFactory
    @DisplayName("checkLevelByDtos Success Tests")
    Stream<DynamicTest> checkLevelByDtosSuccess() {
        levelValidator = new LevelValidator();
        final SoulCharacterTacticRequestDto dto1 = SoulCharacterTacticRequestDto.builder().name("메피").level(101).build();
        final SoulCharacterTacticRequestDto dto2 = SoulCharacterTacticRequestDto.builder().name("미리암").level(181).build();
        final SoulCharacterTacticRequestDto dto3 = SoulCharacterTacticRequestDto.builder().name("무명").level(1).build();
        final List<SoulCharacterTacticRequestDto> value = List.of(dto1, dto2, dto3);

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> levelValidator.checkLevelByDtos(value)
                )
        );
    }

    @TestFactory
    @DisplayName("checkLevelByDtos Fail Tests")
    Stream<DynamicTest> checkLevelByDtosFail() {
        levelValidator = new LevelValidator();
        final SoulCharacterTacticRequestDto dto1 = SoulCharacterTacticRequestDto.builder().name("메피").level(0).build();
        final SoulCharacterTacticRequestDto dto2 = SoulCharacterTacticRequestDto.builder().name("미리암").level(1175).build();
        final SoulCharacterTacticRequestDto dto3 = SoulCharacterTacticRequestDto.builder().name("무명").level(-10).build();
        final List<SoulCharacterTacticRequestDto> value1 = List.of(dto1);
        final List<SoulCharacterTacticRequestDto> value2 = List.of(dto2);
        final List<SoulCharacterTacticRequestDto> value3 = List.of(dto3);

        return Stream.of(value1, value2, value3).map(value ->
            DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                    assertThatThrownBy(() -> levelValidator.checkLevelByDtos(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 레벨"))
        );
    }
}
