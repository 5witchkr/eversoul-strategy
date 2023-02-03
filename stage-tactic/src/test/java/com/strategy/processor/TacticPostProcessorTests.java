package com.strategy.processor;


import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.application.port.inbound.inputdto.souldto.SoulCharacterTacticValidReqDto;
import com.strategy.application.port.inbound.inputdto.tacticdto.TacticRequestDto;
import com.strategy.application.port.outbound.StageOutboundPort;
import com.strategy.application.port.outbound.TacticCharacterOutboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import com.strategy.application.processor.TacticPostProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TacticPostProcessor Tests")
public class TacticPostProcessorTests {

    private TacticPostProcessor tacticPostProcessor;
    private TacticOutboundPort tacticOutboundPort;
    private StageOutboundPort stageOutboundPort;
    private TacticSoulcharacterOutboundPort tacticSoulcharacterOutboundPort;
    private TacticCharacterOutboundPort tacticCharacterOutboundPort;


    @TestFactory
    @DisplayName("postTactic Tests")
    Stream<DynamicTest> postTacticTest() {
        tacticOutboundPort = new TestTacticOutboundPort();
        stageOutboundPort = new TestStageOutboundPort();
        tacticSoulcharacterOutboundPort = new TestTacticSoulcharacterOutboundPort();
        tacticCharacterOutboundPort = new TestTacticCharacterOutboundPort();
        tacticPostProcessor = new TacticPostProcessor(
                tacticOutboundPort,
                stageOutboundPort,
                tacticSoulcharacterOutboundPort,
                tacticCharacterOutboundPort);
        final SoulCharacterTacticValidReqDto soulCharacterTacticValidReqDto1 =
                SoulCharacterTacticValidReqDto.builder().level(10).soulId(1L).build();

        final SoulCharacterTacticValidReqDto soulCharacterTacticValidReqDto2 =
                SoulCharacterTacticValidReqDto.builder().level(20).soulId(2L).build();

        final SoulCharacterTacticValidReqDto soulCharacterTacticValidReqDto3 =
                SoulCharacterTacticValidReqDto.builder().level(30).soulId(3L).build();

        final SoulCharacterTacticValidReqDto soulCharacterTacticValidReqDto4 =
                SoulCharacterTacticValidReqDto.builder().level(40).soulId(4L).build();

        final SoulCharacterTacticValidReqDto soulCharacterTacticValidReqDto5 =
                SoulCharacterTacticValidReqDto.builder().level(50).soulId(5L).build();

        final TacticRequestDto tacticRequestDto = TacticRequestDto.builder()
                .title("제목").info("정보").position("수비")
                .location(1).step(10).power(2000)
                .soulCharacters(List.of(
                        soulCharacterTacticValidReqDto1,
                        soulCharacterTacticValidReqDto2,
                        soulCharacterTacticValidReqDto3,
                        soulCharacterTacticValidReqDto4,
                        soulCharacterTacticValidReqDto5))
                .build();

        final TacticRequestDto tacticFailReqDto = TacticRequestDto.builder()
                .title("제목").info("정보").position("수비")
                .power(2000).soulCharacters(List.of())
                .build();

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: tactic 작성에 성공한다.", () ->
                tacticPostProcessor.postTactic(tacticRequestDto)
                ),
                DynamicTest.dynamicTest("실패케이스: 유효하지 않은 스테이지", () ->
                        assertThatThrownBy(() -> tacticPostProcessor.postTactic(tacticFailReqDto))
                                .isInstanceOf(RuntimeException.class)
                                .hasMessageContaining("유효하지 않은 스테이지")
                )
                );
    }

    private static class TestTacticOutboundPort implements TacticOutboundPort {
        @Override
        public Tactic save(Tactic tactic) {
            return null;
        }

        @Override
        public void deleteById(Long id) {

        }

        @Override
        public Tactic getById(Long tacticId) {
            return null;
        }

        @Override
        public Optional<Tactic> findById(Long tacticId) {
            return Optional.empty();
        }

        @Override
        public Tactic getReferenceById(Long tacticId) {
            return null;
        }

        @Override
        public Long countAll() {
            return null;
        }
    }

    private static class TestStageOutboundPort implements StageOutboundPort {
        @Override
        public Optional<Stage> getByLocationAndStep(int location, int step) {
            if (location == 1 && step == 10) {
                return Optional.of(Stage.builder().location(1).step(10).build());
            }
            return Optional.empty();
        }

        @Override
        public void deleteById(Long id) {

        }

        @Override
        public void save(Stage stage) {

        }

        @Override
        public Optional<Stage> findById(Long id) {
            return Optional.empty();
        }

        @Override
        public boolean existsByLocationAndStep(int location, int step) {
            return false;
        }
    }

    private static class TestTacticSoulcharacterOutboundPort implements TacticSoulcharacterOutboundPort {
        @Override
        public Optional<TacticSoulcharacter> getByName(String name) {
            return Optional.empty();
        }

        @Override
        public void save(TacticSoulcharacter tacticSoulcharacter) {

        }

        @Override
        public void deleteById(Long id) {

        }

        @Override
        public Optional<TacticSoulcharacter> findById(Long id) {
            return Optional.empty();
        }

        @Override
        public boolean existsByName(String name) {
            return false;
        }

        @Override
        public TacticSoulcharacter getByReferenceId(Long id) {
            return null;
        }
    }

    private static class TestTacticCharacterOutboundPort implements TacticCharacterOutboundPort {
        @Override
        public TacticCharacter save(TacticCharacter tacticCharacter) {
            return null;
        }

        @Override
        public List<TacticCharacter> saveAll(List<TacticCharacter> tacticCharacters) {
            return null;
        }

        @Override
        public Long countAll() {
            return null;
        }
    }
}
