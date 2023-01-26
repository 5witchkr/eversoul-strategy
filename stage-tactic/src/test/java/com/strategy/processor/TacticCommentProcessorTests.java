package com.strategy.processor;


import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticComment;
import com.strategy.application.port.inbound.inputdto.TacticCommentRequestDto;
import com.strategy.application.port.inbound.outputdto.TacticCommentResponseDto;
import com.strategy.application.port.outbound.TacticCommentOutboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.processor.TacticCommentProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TacticCommentProcessor Tests")
public class TacticCommentProcessorTests {

    private TacticCommentProcessor tacticCommentProcessor;
    private TacticOutboundPort tacticOutboundPort;
    private TacticCommentOutboundPort tacticCommentOutboundPort;

    @TestFactory
    @DisplayName("getComment Tests")
    Stream<DynamicTest> getCommentTests() {
        tacticOutboundPort = new TestTacticOutboundPort();
        tacticCommentOutboundPort = new TestTacticCommentOutboundPort();
        tacticCommentProcessor = new TacticCommentProcessor(tacticCommentOutboundPort, tacticOutboundPort);

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 해당 tactic 의 commentDto 리스트를 리턴한다.", () -> {
                    final Long tacticId = 1L;
                    final TacticCommentResponseDto tacticCommentResponseDto1 = TacticCommentResponseDto.builder()
                            .username("username1").contents("contents1").build();
                    final TacticCommentResponseDto tacticCommentResponseDto2 = TacticCommentResponseDto.builder()
                            .username("username2").contents("contents2").build();
                    final TacticCommentResponseDto tacticCommentResponseDto3 = TacticCommentResponseDto.builder()
                            .username("username3").contents("contents3").build();

                    List<TacticCommentResponseDto> result =
                            tacticCommentProcessor.getComments(tacticId);

                    assertThat(result.size()).isEqualTo(3);
                    assertThat(result.get(0)).isInstanceOf(TacticCommentResponseDto.class);
                    assertThat(result).usingRecursiveFieldByFieldElementComparator()
                            .contains(tacticCommentResponseDto1,tacticCommentResponseDto2,tacticCommentResponseDto3);
                })
        );
    }
    @TestFactory
    @DisplayName("getComment Tests")
    Stream<DynamicTest> saveCommentTests() {
        tacticOutboundPort = new TestTacticOutboundPort();
        tacticCommentOutboundPort = new TestTacticCommentOutboundPort();
        tacticCommentProcessor = new TacticCommentProcessor(tacticCommentOutboundPort, tacticOutboundPort);

        return Stream.of(
                DynamicTest.dynamicTest("실패케이스: 해당 tactic 이 존재하지 않는경우 NullPointException 반환한다.", () -> {
                    final TacticCommentRequestDto tacticCommentRequestDto = TacticCommentRequestDto.builder()
                            .tacticId(3L).contents("내용").username("유저").build();

                    assertThatThrownBy(() -> tacticCommentProcessor.postComment(tacticCommentRequestDto))
                            .isInstanceOf(NullPointerException.class)
                            .hasMessageContaining("존재하지 않는 Tactic");
                })
        );
    }

    private static class TestTacticOutboundPort implements TacticOutboundPort{

        @Override
        public Tactic save(Tactic tactic) {
            return null;
        }

        @Override
        public void deleteById(Long id) {

        }

        @Override
        public Tactic getById(Long tacticId) {
            final Tactic tactic = Tactic.builder().build();
            final TacticComment tacticComment1 = TacticComment.builder()
                    .username("username1").contents("contents1").tactic(tactic).build();
            final TacticComment tacticComment2 = TacticComment.builder()
                    .username("username2").contents("contents2").tactic(tactic).build();
            final TacticComment tacticComment3 = TacticComment.builder()
                    .username("username3").contents("contents3").tactic(tactic).build();
            tactic.setTacticComments(List.of(tacticComment1,tacticComment2,tacticComment3));
            return tactic;
        }

        @Override
        public Optional<Tactic> findById(Long tacticId) {
            return Optional.empty();
        }
    }

    private static class TestTacticCommentOutboundPort implements TacticCommentOutboundPort{

        @Override
        public void save(TacticComment tacticComment) {

        }
    }

}
