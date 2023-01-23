package com.strategy.facade;

import com.strategy.application.facade.SoulCharacterFacade;
import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import com.strategy.application.processor.TierProcessor;
import com.strategy.application.validator.TierValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
@DisplayName("SoulCharacterFacade Tests")
public class SoulCharacterFacadeTests {

    @Mock
    private TierProcessor tierProcessor;

    @Mock
    private TierValidator tierValidator;

    @InjectMocks
    private SoulCharacterFacade soulCharacterFacade;

    @Nested
    @DisplayName("getSoulByTier 메소드 테스트")
    class getSoulByTierTest {
        @Test
        @DisplayName("success case")
        public void successTest() {
            List<SoulCharacterResponseDto> soulCharacters = List.of(SoulCharacterResponseDto.builder().build());
            given(tierProcessor.getSoulByTier(anyString())).willReturn(soulCharacters);

            soulCharacterFacade.getSoulByTier("S");

            then(tierProcessor).should(times(1)).getSoulByTier(anyString());
            then(tierValidator).should(times(1)).checkInputValue(anyString());
        }
    }
}

