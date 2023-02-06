package com.strategy.processor.position;


import com.strategy.adapter.outbound.persistence.entity.StatisticPosition;
import com.strategy.application.port.inbound.outputdto.PositionStatResponseDto;
import com.strategy.application.port.outbound.StatisticPositionOutboundPort;
import com.strategy.application.processor.position.GetPositionStatProcessor;
import com.strategy.enummodel.TacticPositionEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GetPositionStatProcessor Tests")
public class GetPositionStatProcessorTests {

    private GetPositionStatProcessor getPositionStatProcessor;
    private StatisticPositionOutboundPort statisticPositionOutboundPort;

    @DisplayName("getPositionStat test")
    @TestFactory
    Stream<DynamicTest> getPositionStat() {
        statisticPositionOutboundPort = new TestStatisticPositionOutboundPort();
        getPositionStatProcessor = new GetPositionStatProcessor(statisticPositionOutboundPort);

        final List<PositionStatResponseDto> answer = List.of(
                PositionStatResponseDto.builder()
                        .positionName(TacticPositionEnum.POSITION_3.getValue()).positionCount(40).build(),
                PositionStatResponseDto.builder()
                        .positionName(TacticPositionEnum.POSITION_4.getValue()).positionCount(30).build(),
                PositionStatResponseDto.builder()
                        .positionName(TacticPositionEnum.POSITION_2.getValue()).positionCount(20).build(),
                PositionStatResponseDto.builder()
                        .positionName(TacticPositionEnum.POSITION_1.getValue()).positionCount(10).build()
                );

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: getPositionStat 호출", () -> {

                    List<PositionStatResponseDto> result = getPositionStatProcessor.getPositionStat();

                    assertThat(result.size())
                            .isEqualTo(answer.size());
                    assertThat(result.get(0))
                            .isInstanceOf(PositionStatResponseDto.class);
                    assertThat(result).usingRecursiveFieldByFieldElementComparator()
                            .isEqualTo(answer);
                })
        );
    }

    private static class TestStatisticPositionOutboundPort implements StatisticPositionOutboundPort {
        @Override
        public StatisticPosition getReferenceById(Long id) {
            return null;
        }

        @Override
        public void save(StatisticPosition statisticPosition) {

        }

        @Override
        public List<StatisticPosition> findAll() {
            return List.of(
                    StatisticPosition.builder()
                            .positionName(TacticPositionEnum.POSITION_1.getValue())
                            .positionCount(10).build(),
                    StatisticPosition.builder()
                            .positionName(TacticPositionEnum.POSITION_2.getValue())
                            .positionCount(20).build(),
                    StatisticPosition.builder()
                            .positionName(TacticPositionEnum.POSITION_3.getValue())
                            .positionCount(40).build(),
                    StatisticPosition.builder()
                            .positionName(TacticPositionEnum.POSITION_4.getValue())
                            .positionCount(30).build()
            );
        }
    }
}
