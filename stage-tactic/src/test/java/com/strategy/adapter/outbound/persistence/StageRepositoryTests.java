package com.strategy.adapter.outbound.persistence;


import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.adpater.outbound.persistence.repository.StageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("StageRepository Tests")
public class StageRepositoryTests {

    @Autowired
    private StageRepository stageRepository;

    @TestFactory
    @DisplayName("getByLocationAndStep Tests")
    Stream<DynamicTest> getByLocationAndStep() {
        final Stage stage1 = Stage.builder()
                .location("10").step("10").position("기본").power("111").soulCharacters("메피,나이아")
                .build();
        final Stage stage2 = Stage.builder()
                .location("11").step("20").position("저격").power("222").soulCharacters("나이아,순이")
                .build();
        final Stage stage3 = Stage.builder()
                .location("13").step("15").position("수비").power("333").soulCharacters("아드리안,프림")
                .build();
        stageRepository.save(stage1);
        stageRepository.save(stage2);
        stageRepository.save(stage3);

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 해당 스테이지 정보를 가져온다.", () -> {
                    String location = "11";
                    String step = "20";

                    Optional<Stage> result = stageRepository.getByLocationAndStep(location, step);

                    assertThat(result).isPresent();
                    result.ifPresent(resultValue -> {
                        assertThat(resultValue).isInstanceOf(Stage.class);
                        assertThat(resultValue).usingRecursiveComparison().isEqualTo(stage2);
                    });
                })
        );
    }
}
