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
        return Stream.of(DynamicTest.dynamicTest("성공케이스: ", () -> {

            SoulSelectResponseDto result = getSoulSelectStatProcessor.getOne(inputSoulId);

            assertThat(result).isInstanceOf(SoulSelectResponseDto.class);
            assertThat(result).usingRecursiveComparison().isEqualTo(answer);
        })
        );
    }

    private static class TestStatisticSoulSelectOutboundPort implements StatisticSoulSelectOutboundPort {
        @Override
        public StatisticSoulselect getReferenceById(Long id) {
            return StatisticSoulselect.builder().id(2L).selectCount(30).build();
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
