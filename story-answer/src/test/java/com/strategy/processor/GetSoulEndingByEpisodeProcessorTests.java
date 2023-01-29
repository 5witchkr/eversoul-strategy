package com.strategy.processor;


import com.strategy.adapter.outbound.persistence.entity.StoryAnswer;
import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StoryQuestion;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.application.port.inbound.outputdto.StoryAnswerResponseDto;
import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;
import com.strategy.application.port.outbound.StoryEpisodeOutboundPort;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import com.strategy.application.processor.GetSoulEndingByEpisodeProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("GetSoulEndingByEpisodeProcessor Tests")
public class GetSoulEndingByEpisodeProcessorTests {

    private GetSoulEndingByEpisodeProcessor getSoulEndingByEpisodeProcessor;
    private StorySoulcharacterOutboundPort storySoulcharacterOutboundPort;
    private StoryEpisodeOutboundPort storyEpisodeOutboundPort;

    @TestFactory
    @DisplayName("getSoulEndingAllEpisode Tests")
    Stream<DynamicTest> getSoulEndingAllEpisode() {
        storySoulcharacterOutboundPort = new TestStorySoulcharacterOutboundPort();
        storyEpisodeOutboundPort = new TestStoryEpisodeOutboundPort();
        getSoulEndingByEpisodeProcessor = new GetSoulEndingByEpisodeProcessor(
                storySoulcharacterOutboundPort, storyEpisodeOutboundPort);

        final Long soulId = 1L;

        final StoryQuestionEndingResponseDto questionInfoDto1 =
                StoryQuestionEndingResponseDto.builder().orderNumber(1).storyAnswerResponseDtos(
                        List.of(StoryAnswerResponseDto.builder().orderNumber(2).build())
                ).build();
        final StoryQuestionEndingResponseDto questionInfoDto2 =
                StoryQuestionEndingResponseDto.builder().orderNumber(2).storyAnswerResponseDtos(
                       List.of(StoryAnswerResponseDto.builder().orderNumber(3).build())
                ).build();
        final StoryQuestionEndingResponseDto questionInfoDto3 =
                StoryQuestionEndingResponseDto.builder().orderNumber(3).storyAnswerResponseDtos(
                        List.of(StoryAnswerResponseDto.builder().orderNumber(1).build())
                ).build();

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스1: 해당정령의 모든 에피소드 트루엔딩을 리턴한다.", () -> {

                    List<StoryQuestionEndingResponseDto> result =
                            getSoulEndingByEpisodeProcessor.getSoulEndingAllEpisode(soulId, "TRUE");

                    assertThat(result.size()).isEqualTo(3);
                    assertThat(result.get(0)).isInstanceOf(StoryQuestionEndingResponseDto.class);
                    assertThat(result).usingRecursiveFieldByFieldElementComparator()
                            .contains(questionInfoDto1,questionInfoDto2,questionInfoDto3);
                }),
                DynamicTest.dynamicTest("성공케이스2: 해당정령의 모든 에피소드 배드엔딩을 리턴한다.", () -> {
                })
        );
    }

    private class TestStorySoulcharacterOutboundPort implements StorySoulcharacterOutboundPort {
        @Override
        public StorySoulcharacter getByName(String soul) {
            return null;
        }

        @Override
        public void save(StorySoulcharacter storySoulcharacter) {

        }

        @Override
        public void deleteById(Long id) {

        }

        @Override
        public Optional<StorySoulcharacter> findById(Long storySoulcharacterId) {
            return Optional.empty();
        }

        @Override
        public StorySoulcharacter getByReferenceId(Long soulId) {
            return null;
        }
    }

    private class TestStoryEpisodeOutboundPort implements StoryEpisodeOutboundPort {
        @Override
        public StoryEpisode getByOrderNumberAndStorySoulcharacter(int orderNumber, StorySoulcharacter storySoulcharacter) {

            final StorySoulcharacter storySoulcharacterData = StorySoulcharacter.builder().id(1L).name("아드리안").build();

            final StoryEpisode storyEpisode = StoryEpisode.builder().orderNumber(0)
                    .storySoulcharacter(storySoulcharacterData)
                    .build();

            //Episode 에 있는 질문목록
            final StoryQuestion storyQuestion1 = StoryQuestion.builder().storyEpisode(storyEpisode).orderNumber(1).build();
            final StoryQuestion storyQuestion2 = StoryQuestion.builder().storyEpisode(storyEpisode).orderNumber(2).build();
            final StoryQuestion storyQuestion3 = StoryQuestion.builder().storyEpisode(storyEpisode).orderNumber(3).build();

            //각 질문에 있는 답 선택 목록
            final StoryAnswer storyAnswer1_1 = StoryAnswer.builder().storyQuestion(storyQuestion1).orderNumber(1).bestAnswer(0).build();
            final StoryAnswer storyAnswer1_2 = StoryAnswer.builder().storyQuestion(storyQuestion1).orderNumber(2).bestAnswer(1).build();
            final StoryAnswer storyAnswer1_3 = StoryAnswer.builder().storyQuestion(storyQuestion1).orderNumber(3).bestAnswer(0).build();
            final StoryAnswer storyAnswer2_1 = StoryAnswer.builder().storyQuestion(storyQuestion2).orderNumber(1).bestAnswer(0).build();
            final StoryAnswer storyAnswer2_2 = StoryAnswer.builder().storyQuestion(storyQuestion2).orderNumber(2).bestAnswer(0).build();
            final StoryAnswer storyAnswer2_3 = StoryAnswer.builder().storyQuestion(storyQuestion2).orderNumber(3).bestAnswer(1).build();
            final StoryAnswer storyAnswer3_1 = StoryAnswer.builder().storyQuestion(storyQuestion3).orderNumber(1).bestAnswer(1).build();
            final StoryAnswer storyAnswer3_2 = StoryAnswer.builder().storyQuestion(storyQuestion3).orderNumber(2).bestAnswer(0).build();

            //로직에서 orderNumber 를 기준으로 순서대로 정렬 해줘야 하기 때문에, 순서에 상관없이 set
            storyQuestion1.setStoryAnswers(List.of(storyAnswer1_3,storyAnswer1_2,storyAnswer1_1));
            storyQuestion2.setStoryAnswers(List.of(storyAnswer2_1,storyAnswer2_3,storyAnswer2_2));
            storyQuestion3.setStoryAnswers(List.of(storyAnswer3_2,storyAnswer3_1));

            storyEpisode.setStoryQuestions(List.of(storyQuestion1,storyQuestion3,storyQuestion2));

            return storyEpisode;
        }

        @Override
        public void save(StoryEpisode storyEpisode) {

        }

        @Override
        public Optional<StoryEpisode> findById(Long storyEpisodeId) {
            return Optional.empty();
        }

        @Override
        public void deleteById(Long id) {

        }

        @Override
        public StoryEpisode getByOrderNumberAndStorySoulcharacter_Id(int i, Long storySoulcharacter) {

            return null;
        }
    }
}
