package com.strategy.processor.soulselect;


import com.strategy.adapter.outbound.persistence.entity.StatisticSoulselect;
import com.strategy.application.port.inbound.outputdto.SoulSelectResponseDto;
import com.strategy.application.port.outbound.StatisticSoulSelectOutboundPort;
import com.strategy.application.processor.soulselect.GetSoulSelectStatProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("GetSoulSelectStatProcessor Tests")
public class GetSoulSelectStatProcessorTests {

    private GetSoulSelectStatProcessor getSoulSelectStatProcessor;
    private StatisticSoulSelectOutboundPort statisticSoulSelectOutboundPort;

    @DisplayName("getOne tests")
    @TestFactory
    Stream<DynamicTest> testGetOne() {
        statisticSoulSelectOutboundPort = new TestStatisticSoulSelectOutboundPort();
        getSoulSelectStatProcessor = new GetSoulSelectStatProcessor(statisticSoulSelectOutboundPort);
        final Long inputSoulId = 2L;
        final SoulSelectResponseDto answer = SoulSelectResponseDto.builder().id(2L).selectCount(30).build();

        return Stream.of(DynamicTest.dynamicTest("성공케이스: SoulSelectResponseDto를 반환한다", () -> {

                    SoulSelectResponseDto result = getSoulSelectStatProcessor.getOne(inputSoulId);

                    assertThat(result).isInstanceOf(SoulSelectResponseDto.class);
                    assertThat(result).usingRecursiveComparison().isEqualTo(answer);
                }),
                DynamicTest.dynamicTest("실패케이스: 존재하지 않는 soulId", () -> {
                    assertThatThrownBy(
                            () -> getSoulSelectStatProcessor.getOne(1100L))
                            .isInstanceOf(NullPointerException.class);
                })
        );
    }

    @DisplayName("getTopRating tests")
    @TestFactory
    Stream<DynamicTest> testGetTopRating() {
        statisticSoulSelectOutboundPort = new TestStatisticSoulSelectOutboundPort();
        getSoulSelectStatProcessor = new GetSoulSelectStatProcessor(statisticSoulSelectOutboundPort);
        final List<SoulSelectResponseDto> answer = List.of(
                SoulSelectResponseDto.builder().id(4L).selectCount(5).build(),
                SoulSelectResponseDto.builder().id(3L).selectCount(4).build(),
                SoulSelectResponseDto.builder().id(1L).selectCount(3).build()
        );
        final int argNumber = 3;

        return Stream.of(DynamicTest.dynamicTest("성공케이스: topRating soulselect를 반환한다.", () -> {
            List<SoulSelectResponseDto> result = getSoulSelectStatProcessor.getTopRating(argNumber);

            assertThat(result.size()).isEqualTo(answer.size());
            assertThat(result.get(0)).isInstanceOf(SoulSelectResponseDto.class);
            assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(answer);
        }));
    }

    @DisplayName("getBottomRating tests")
    @TestFactory
    Stream<DynamicTest> testGetBottomRating() {
        statisticSoulSelectOutboundPort = new TestStatisticSoulSelectOutboundPort();
        getSoulSelectStatProcessor = new GetSoulSelectStatProcessor(statisticSoulSelectOutboundPort);
        final List<SoulSelectResponseDto> answer = List.of(
                SoulSelectResponseDto.builder().id(2L).selectCount(2).build(),
                SoulSelectResponseDto.builder().id(1L).selectCount(3).build(),
                SoulSelectResponseDto.builder().id(3L).selectCount(4).build()
        );
        final int argNumber = 3;

        return Stream.of(DynamicTest.dynamicTest("성공케이스: bottomRating soulselect를 반환한다.", () -> {
            List<SoulSelectResponseDto> result = getSoulSelectStatProcessor.getBottomRating(argNumber);

            assertThat(result.size()).isEqualTo(answer.size());
            assertThat(result.get(0)).isInstanceOf(SoulSelectResponseDto.class);
            assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(answer);
        }));
    }

    private static class TestStatisticSoulSelectOutboundPort implements StatisticSoulSelectOutboundPort {
        @Override
        public StatisticSoulselect getReferenceById(Long id) {
            if (id == 2L){
                return StatisticSoulselect.builder().id(2L).selectCount(30).build();
            }
            return null;
        }

        @Override
        public void save(StatisticSoulselect statisticSoulselect) {

        }

        @Override
        public List<StatisticSoulselect> findAll() {
            return List.of(
                    StatisticSoulselect.builder().id(1L).selectCount(3).build(),
                    StatisticSoulselect.builder().id(2L).selectCount(2).build(),
                    StatisticSoulselect.builder().id(3L).selectCount(4).build(),
                    StatisticSoulselect.builder().id(4L).selectCount(5).build()
                    );
        }
    }
}
