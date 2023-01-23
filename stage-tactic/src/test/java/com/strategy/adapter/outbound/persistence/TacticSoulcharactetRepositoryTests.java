package com.strategy.adapter.outbound.persistence;

import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.adpater.outbound.persistence.repository.TacticSoulcharacterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("TacticSoulcharactetRepository Tests")
public class TacticSoulcharactetRepositoryTests {

    @Autowired
    private TacticSoulcharacterRepository tacticSoulcharacterRepository;

    @TestFactory
    @DisplayName("getByName Tests")
    Stream<DynamicTest> getByName() {
        final TacticSoulcharacter tacticSoulcharacter1 = TacticSoulcharacter.builder()
                .id(1L).name("메피").tier("SS").type("인간")
                .build();
        final TacticSoulcharacter tacticSoulcharacter2 = TacticSoulcharacter.builder()
                .id(2L).name("미리암").tier("SS").type("요정")
                .build();
        final TacticSoulcharacter tacticSoulcharacter3 = TacticSoulcharacter.builder()
                .id(3L).name("지호").tier("SS").type("인간")
                .build();

        tacticSoulcharacterRepository.save(tacticSoulcharacter1);
        tacticSoulcharacterRepository.save(tacticSoulcharacter2);
        tacticSoulcharacterRepository.save(tacticSoulcharacter3);

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 해당 정령의 정보를 가져온다.", () -> {
                    String name = "지호";

                    Optional<TacticSoulcharacter> result = tacticSoulcharacterRepository.getByName(name);

                    assertThat(result).isPresent();
                    result.ifPresent(resultValue -> {
                        assertThat(resultValue).isInstanceOf(TacticSoulcharacter.class);
                        assertThat(resultValue).usingRecursiveComparison().isEqualTo(tacticSoulcharacter3);
                    });
                })
        );
    }
}