package com.strategy.validator;

import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticValidReqDto;
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
        final SoulCharacterTacticValidReqDto dto1 = SoulCharacterTacticValidReqDto.builder().soulId(1L).level(101).build();
        final SoulCharacterTacticValidReqDto dto2 = SoulCharacterTacticValidReqDto.builder().soulId(2L).level(181).build();
        final SoulCharacterTacticValidReqDto dto3 = SoulCharacterTacticValidReqDto.builder().soulId(5L).level(1).build();
        final List<SoulCharacterTacticValidReqDto> value = List.of(dto1, dto2, dto3);

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
        final SoulCharacterTacticValidReqDto dto1 = SoulCharacterTacticValidReqDto.builder().soulId(1L).level(-199).build();
        final SoulCharacterTacticValidReqDto dto2 = SoulCharacterTacticValidReqDto.builder().soulId(2L).level(2002).build();
        final SoulCharacterTacticValidReqDto dto3 = SoulCharacterTacticValidReqDto.builder().soulId(5L).level(-1).build();
        final List<SoulCharacterTacticValidReqDto> value1 = List.of(dto1);
        final List<SoulCharacterTacticValidReqDto> value2 = List.of(dto2);
        final List<SoulCharacterTacticValidReqDto> value3 = List.of(dto3);

        return Stream.of(value1, value2, value3).map(value ->
            DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                    assertThatThrownBy(() -> levelValidator.checkLevelByDtos(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 레벨"))
        );
    }
}
