package com.strategy.adapter.outbound.persistence;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("SoulCharacterRepository Tests")
public class SoulCharacterRepositoryTests {

    @Autowired
    private SoulCharacterRepository soulCharacterRepository;

    @TestFactory
    @DisplayName("SoulCharacterRepository getByTier Tests")
    Stream<DynamicTest> getByTier(){
        final SoulCharacter soulCharacter1 = SoulCharacter.builder()
                .id(1L).name("탈리아").type("요정").tier("SSS")
                .build();
        final SoulCharacter soulCharacter2 = SoulCharacter.builder()
                .id(2L).name("미리암").type("요정").tier("SSS")
                .build();
        final SoulCharacter soulCharacter3 = SoulCharacter.builder()
                .id(3L).name("린지").type("인간").tier("S")
                .build();
        soulCharacterRepository.save(soulCharacter1);
        soulCharacterRepository.save(soulCharacter2);
        soulCharacterRepository.save(soulCharacter3);

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 해당티어 리스트를 가져온다.", () -> {
                    String tierValue = "SSS";
                    int resultedSize = 2;
                    Optional<List<SoulCharacter>> result = soulCharacterRepository.getByTier(tierValue);

                    assertThat(result).isPresent();
                    result.ifPresent(resultValue -> {
                        assertThat(resultValue.size())
                                .isEqualTo(resultedSize);
                        assertThat(resultValue.get(0))
                                .isInstanceOf(SoulCharacter.class);
                        assertThat(resultValue).usingRecursiveFieldByFieldElementComparator()
                                .contains(soulCharacter1, soulCharacter2);
                    });
                }),
                DynamicTest.dynamicTest("성공케이스: 빈 배열 반환", () -> {
                    String tierValue = "A";
                    Optional<List<SoulCharacter>> result = soulCharacterRepository.getByTier(tierValue);

                    assertThat(result).isPresent();
                    result.ifPresent(resultValue -> assertThat(resultValue).isEqualTo(List.of()));
                })
        );
    }
}
