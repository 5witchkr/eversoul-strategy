package com.strategy.processor.position;


import com.strategy.adapter.outbound.persistence.entity.StatisticPosition;
import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.application.port.outbound.StatisticPositionOutboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.processor.position.StatisticPositionProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StatisticPositionProcessor Tests")
public class StatisticPositionProcessorTests {

    private StatisticPositionProcessor statisticPositionProcessor;
    private TacticOutboundPort tacticOutboundPort;
    private StatisticPositionOutboundPort statisticPositionOutboundPort;

    @DisplayName("successCase test")
    @TestFactory
    Stream<DynamicTest> successCase() {
        statisticPositionOutboundPort = new TestStatisticPositionOutboundPort();
        tacticOutboundPort = new TestTacticOutboundPort();
        statisticPositionProcessor = new StatisticPositionProcessor(tacticOutboundPort, statisticPositionOutboundPort);

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 추가된 컬럼의 갯수를 리턴한다.", () -> {
                    Long result = statisticPositionProcessor.getAddedCount();
                    assertThat(result).isEqualTo(19L);
                }),

                DynamicTest.dynamicTest("성공케이스: saveLastCount 호출", () -> {
                    statisticPositionProcessor.saveLastCount();
                })
        );
    }

    private static class TestStatisticPositionOutboundPort implements StatisticPositionOutboundPort {
        @Override
        public StatisticPosition getReferenceById(Long id) {
            return StatisticPosition.builder().lastBatch(21L).build();
        }

        @Override
        public void save(StatisticPosition statisticPosition) {

        }

        @Override
        public List<StatisticPosition> findAll() {
            return null;
        }
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
            return 40L;
        }
    }
}
