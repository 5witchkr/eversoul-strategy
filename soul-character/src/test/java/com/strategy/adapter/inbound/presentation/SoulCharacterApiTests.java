package com.strategy.adapter.inbound.presentation;


import com.strategy.application.facade.SoulCharacterPortFacade;
import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(SoulCharacterApi.class)
public class SoulCharacterApiTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SoulCharacterPortFacade soulCharacterPortFacade;


    @DisplayName("getSoulByTier Test")
    @TestFactory
    Stream<DynamicTest> getSoulByTierTest() {
        soulCharacterPortFacade = new TestSoulCharacterPortFacade();
        final String targetTierValue = "SS";
        return Stream.of(
                DynamicTest.dynamicTest("정령의 티어를 조회한다.", () -> {
                    ResultActions actions = mockMvc.perform(
                            get("/api/soulcharacter/tier")
                                    .param("tier",targetTierValue)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    );
                    actions.andExpect(status().isOk());
                })
        );
    }

    private class TestSoulCharacterPortFacade implements SoulCharacterPortFacade {
        @Override
        public List<SoulCharacterResponseDto> getSoulByTier(String tier) {
            return List.of(SoulCharacterResponseDto.builder().tier("SS").build());
        }
    }
}
