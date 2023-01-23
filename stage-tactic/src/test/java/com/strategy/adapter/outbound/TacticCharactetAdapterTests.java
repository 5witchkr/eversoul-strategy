package com.strategy.adapter.outbound;

import com.strategy.adpater.outbound.TacticCharacterAdapter;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.adpater.outbound.persistence.repository.TacticCharacterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
@DisplayName("TacticCharactetAdapter Tests")
public class TacticCharactetAdapterTests {

    @Mock
    private TacticCharacterRepository tacticCharacterRepository;

    @InjectMocks
    private TacticCharacterAdapter tacticCharacterAdapter;

    @Nested
    @DisplayName("save 메소드 호출 테스트")
    class saveTest {
        @Test
        @DisplayName("success case")
        public void successTest() {
            TacticCharacter tacticCharacter = TacticCharacter.builder().build();
            given(tacticCharacterRepository.save(Mockito.any(TacticCharacter.class)))
                    .willReturn(tacticCharacter);

            tacticCharacterAdapter.save(tacticCharacter);

            then(tacticCharacterRepository).should(times(1))
                    .save(Mockito.any(TacticCharacter.class));
        }
    }
}
