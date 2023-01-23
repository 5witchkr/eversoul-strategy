package com.strategy.adapter.outbound;

import com.strategy.adpater.outbound.TacticSoulcharacterAdapter;
import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.adpater.outbound.persistence.repository.TacticSoulcharacterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("TacticSoulcharacterAdapter Tests")
public class TacticSoulcharacterAdapterTests {
    @Mock
    private TacticSoulcharacterRepository tacticSoulcharacterRepository;

    @InjectMocks
    private TacticSoulcharacterAdapter tacticSoulcharacterAdapter;

    @Nested
    @DisplayName("getByName 메소드 호출 테스트")
    class getByNameTests {
        @Test
        @DisplayName("success case")
        public void successTest() {
            TacticSoulcharacter tacticSoulcharacter = TacticSoulcharacter.builder().build();
            given(tacticSoulcharacterRepository.getByName(anyString()))
                    .willReturn(Optional.of(tacticSoulcharacter));

            tacticSoulcharacterAdapter.getByName("메피");

            then(tacticSoulcharacterRepository).should(times(1))
                    .getByName(anyString());
        }
    }
}

