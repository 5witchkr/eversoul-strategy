package com.strategy.adapter.outbound;



import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.adapter.outbound.persistence.SoulCharacterRepository;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("SoulCharacterAdapter Tests")
public class SoulCharacterAdapterTests {

    @Mock
    private SoulCharacterRepository soulCharacterRepository;

    @InjectMocks
    private SoulCharacterAdapter soulCharacterAdapter;

    @Nested
    @DisplayName("getSoulTier 메소드 테스트")
    class getSoulTierTest {
        @Test
        @DisplayName("success case")
        public void successTest() {
            List<SoulCharacter> soulCharacters = List.of(SoulCharacter.builder().build());
            given(soulCharacterRepository.getByTier(anyString())).willReturn(Optional.of(soulCharacters));

            soulCharacterAdapter.getByTier("S");

            then(soulCharacterRepository).should(times(1)).getByTier(anyString());
        }
    }
}
