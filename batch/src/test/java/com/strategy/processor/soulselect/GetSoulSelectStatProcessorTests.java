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
            return null;
        }
    }
}
