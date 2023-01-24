package com.strategy.processor;

import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import com.strategy.application.port.inbound.outputdto.SoulCharacterTacticResponseDto;
import com.strategy.application.port.outbound.StageOutboundPort;
import com.strategy.application.processor.RecommendProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RecommendProcessor Tests")
public class RecommendProcessorTests {

    private StageOutboundPort stageOutboundPort;
    private RecommendProcessor recommendProcessor;

    @TestFactory
    @DisplayName("getRecommendWithoutBans Tests")
    Stream<DynamicTest> getRecommendWithoutBans() {
        stageOutboundPort = new TestStageOutboundPort();
        recommendProcessor = new RecommendProcessor(stageOutboundPort);
        final SoulCharacterTacticResponseDto soulCharacterTacticResponseDto1 = SoulCharacterTacticResponseDto.builder()
                .name("메피").level(111).build();
        final SoulCharacterTacticResponseDto soulCharacterTacticResponseDto2 = SoulCharacterTacticResponseDto.builder()
                .name("지호").level(112).build();
        final SoulCharacterTacticResponseDto soulCharacterTacticResponseDto3 = SoulCharacterTacticResponseDto.builder()
                .name("아드리안").level(113).build();
        final SoulCharacterTacticResponseDto soulCharacterTacticResponseDto4 = SoulCharacterTacticResponseDto.builder()
                .name("미리암").level(114).build();
        final SoulCharacterTacticResponseDto soulCharacterTacticResponseDto5 = SoulCharacterTacticResponseDto.builder()
                .name("클로이").level(115).build();
        final SoulCharacterTacticResponseDto soulCharacterTacticResponseDto6 = SoulCharacterTacticResponseDto.builder()
                .name("나이아").level(116).build();
        final RecommendTacticResponseDto recommendTacticResponseDto1 = RecommendTacticResponseDto.builder()
                .position("기본").power(123).info("정보1")
                .soulCharacterTacticResponseDtos(List.of(
                        soulCharacterTacticResponseDto1,
                        soulCharacterTacticResponseDto2,
                        soulCharacterTacticResponseDto3)).build();
        final RecommendTacticResponseDto recommendTacticResponseDto2 = RecommendTacticResponseDto.builder()
                .position("수비").power(234).info("정보2")
                .soulCharacterTacticResponseDtos(List.of(
                        soulCharacterTacticResponseDto4,
                        soulCharacterTacticResponseDto5,
                        soulCharacterTacticResponseDto6)).build();


        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 해당스테이지 추천픽을 2개 리턴한다.", () -> {
                    final int location = 11;
                    final int step = 30;
                    final List<String> bans = List.of("플랑","아야메");

                    List<RecommendTacticResponseDto> result =
                            recommendProcessor.getRecommendWithoutBans(location, step, bans);

                    assertThat(result.size()).isEqualTo(2);
                    assertThat(result.get(0)).isInstanceOf(RecommendTacticResponseDto.class);
                    assertThat(result).usingRecursiveFieldByFieldElementComparator().contains(recommendTacticResponseDto1, recommendTacticResponseDto2);
                }),
                DynamicTest.dynamicTest("성공케이스: 해당스테이지 추천픽을 하나 필터링하고 1개만 리턴한다.", () -> {
                    final int location = 11;
                    final int step = 30;
                    final List<String> bans = List.of("아드리안","아야메");

                    List<RecommendTacticResponseDto> result =
                            recommendProcessor.getRecommendWithoutBans(location, step, bans);

                    assertThat(result.size()).isEqualTo(1);
                    assertThat(result.get(0)).isInstanceOf(RecommendTacticResponseDto.class);
                    assertThat(result).usingRecursiveFieldByFieldElementComparator().contains(recommendTacticResponseDto2);
                }),
                DynamicTest.dynamicTest("성공케이스: 해당스테이지 추천픽을 모두 필터링하고 0개 리턴한다.", () -> {
                    final int location = 11;
                    final int step = 30;
                    final List<String> bans = List.of("아드리안","아야메","메피","지호","미리암","나이아");

                    List<RecommendTacticResponseDto> result =
                            recommendProcessor.getRecommendWithoutBans(location, step, bans);

                    assertThat(result.size()).isEqualTo(0);
                })
        );
    }


    private static class TestStageOutboundPort implements StageOutboundPort {
        final TacticSoulcharacter tacticSoulcharacter1 = TacticSoulcharacter.builder().name("메피").build();
        final TacticSoulcharacter tacticSoulcharacter2 = TacticSoulcharacter.builder().name("지호").build();
        final TacticSoulcharacter tacticSoulcharacter3 = TacticSoulcharacter.builder().name("아드리안").build();
        final TacticSoulcharacter tacticSoulcharacter4 = TacticSoulcharacter.builder().name("미리암").build();
        final TacticSoulcharacter tacticSoulcharacter5 = TacticSoulcharacter.builder().name("클로이").build();
        final TacticSoulcharacter tacticSoulcharacter6 = TacticSoulcharacter.builder().name("나이아").build();
        final TacticCharacter tacticCharacter1 = TacticCharacter.builder().tacticSoulcharacter(tacticSoulcharacter1).level(111).build();
        final TacticCharacter tacticCharacter2 = TacticCharacter.builder().tacticSoulcharacter(tacticSoulcharacter2).level(112).build();
        final TacticCharacter tacticCharacter3 = TacticCharacter.builder().tacticSoulcharacter(tacticSoulcharacter3).level(113).build();
        final TacticCharacter tacticCharacter4 = TacticCharacter.builder().tacticSoulcharacter(tacticSoulcharacter4).level(114).build();
        final TacticCharacter tacticCharacter5 = TacticCharacter.builder().tacticSoulcharacter(tacticSoulcharacter5).level(115).build();
        final TacticCharacter tacticCharacter6 = TacticCharacter.builder().tacticSoulcharacter(tacticSoulcharacter6).level(116).build();
        final Tactic tactic1 = Tactic.builder().power(123).info("정보1").position("기본").tacticCharacters(List.of(tacticCharacter1,tacticCharacter2,tacticCharacter3)).build();
        final Tactic tactic2 = Tactic.builder().power(234).info("정보2").position("수비").tacticCharacters(List.of(tacticCharacter4,tacticCharacter5,tacticCharacter6)).build();
        final Stage stage = Stage.builder()
                .position("기본").power(10000).location(11).step(30).tactics(List.of(tactic1, tactic2)).build();
        @Override
        public Optional<Stage> getByLocationAndStep(int location, int step) {
            return Optional.of(stage);
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
    }
}
