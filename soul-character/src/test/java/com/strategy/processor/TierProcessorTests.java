package com.strategy.processor;


import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import com.strategy.application.port.outbound.SoulCharacterOutboundPort;
import com.strategy.application.processor.TierProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TierProcessor Tests")
public class TierProcessorTests {

    private SoulCharacterOutboundPort soulCharacterOutboundPort;
    private TierProcessor tierProcessor;

    @TestFactory
    @DisplayName("TierProcessor getSoulByTier Tests")
    Stream<DynamicTest> getSoulByTier() {
        soulCharacterOutboundPort = new TestSoulCharacterOutboundPort();
        tierProcessor = new TierProcessor(soulCharacterOutboundPort);

        final SoulCharacterResponseDto soulCharacterDto1 = SoulCharacterResponseDto.builder()
                .id(1L).name("탈리아").type("요정").tier("SSS")
                .build();
        final SoulCharacterResponseDto soulCharacterDto2 = SoulCharacterResponseDto.builder()
                .id(2L).name("미리암").type("요정").tier("SSS")
                .build();

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스: 해당티어 정령리스트를 리턴한다.", () -> {
                    List<SoulCharacterResponseDto> soulCharacterResponseDtos = tierProcessor.getSoulByTier("SSS");

                    assertThat(soulCharacterResponseDtos.size())
                            .isEqualTo(2);
                    assertThat(soulCharacterResponseDtos.get(0))
                            .isInstanceOf(SoulCharacterResponseDto.class);
                    assertThat(soulCharacterResponseDtos).usingRecursiveFieldByFieldElementComparator()
                            .contains(soulCharacterDto1, soulCharacterDto2);
                })
        );
    }

    private static class TestSoulCharacterOutboundPort implements SoulCharacterOutboundPort {
        final SoulCharacter soulCharacter1 = SoulCharacter.builder()
                .id(1L).name("탈리아").type("요정").tier("SSS")
                .build();
        final SoulCharacter soulCharacter2 = SoulCharacter.builder()
                .id(2L).name("미리암").type("요정").tier("SSS")
                .build();
        @Override
        public Optional<List<SoulCharacter>> getByTier(String tier) {
            return Optional.of(List.of(soulCharacter1, soulCharacter2));
        }
    }
}

