package com.strategy.validoator;

import com.strategy.application.validator.StoryEpisodeValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StoryEpisodeValidatorTests {
    @TestFactory
    @DisplayName("checkEpisode Success Tests")
    Stream<DynamicTest> checkEpisodeSuccess() {
        StoryEpisodeValidator storyEpisodeValidator = new StoryEpisodeValidator();
        final List<Integer> valueSource = IntStream.rangeClosed(1, 30)
                .boxed()
                .collect(Collectors.toList());

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("성공케이스: validator를 통과한다.",
                        () -> storyEpisodeValidator.checkEpisode(value)));
    }

    @TestFactory
    @DisplayName("checkEpisode Fail Tests")
    Stream<DynamicTest> checkEpisodeFail() {
       StoryEpisodeValidator storyEpisodeValidator = new StoryEpisodeValidator();
        final List<Integer> source1 = IntStream.rangeClosed(31, 40)
                .boxed()
                .collect(Collectors.toList());
        final List<Integer> source2 = List.of( -1, -10, 500);

        final List<Integer> valueSource = Stream.of(source1, source2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return valueSource.stream()
                .map(value -> DynamicTest.dynamicTest("실패케이스: IllegalException을 반환한다.", () ->
                        assertThatThrownBy(() -> storyEpisodeValidator.checkEpisode(value))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessageContaining("유효하지 않은 에피소드 값")));
    }
}
