package com.strategy.processor;

import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.processor.TacticRecommendCountProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TacticRecommendCountProcessor Tests")
public class TacticRecommendCountProcessorTests {

    private TacticRecommendCountProcessor tacticRecommendCountProcessor;
    private TacticOutboundPort tacticOutboundPort;

    @TestFactory
    @DisplayName("addRecommend Tests")
    Stream<DynamicTest> addRecommend() {
        tacticOutboundPort = new TestTacticOutBoundPort();
        tacticRecommendCountProcessor = new TacticRecommendCountProcessor(tacticOutboundPort);

        Long existsTacticId = 2L;
        Long notExistsTacticId = 3L;

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: tacticId가 존재할경우",
                        () -> tacticRecommendCountProcessor.addRecommend(existsTacticId)
                ),

                DynamicTest.dynamicTest("실패케이스: 존재하지 않는 tactic일 경우 IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> tacticRecommendCountProcessor.addRecommend(notExistsTacticId))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("존재하지 않는 게시글"))
        );
    }

    private static class TestTacticOutBoundPort implements TacticOutboundPort {
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
            if (tacticId == 2L) {
                return Tactic.builder().id(2L).build();
            }
            return null;
        }

        @Override
        public Long countAll() {
            return null;
        }
    }
}
