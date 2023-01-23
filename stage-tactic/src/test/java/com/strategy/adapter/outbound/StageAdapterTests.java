package com.strategy.adapter.outbound;

import com.strategy.adpater.outbound.StageAdapter;
import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.adpater.outbound.persistence.repository.StageRepository;
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
@DisplayName("StageAdapter Tests")
public class StageAdapterTests {
    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageAdapter stageAdapter;

    @Nested
    @DisplayName("getByLocationAndStep 메소드 호출 테스트")
    class getByLocationAndStepTest {
        @Test
        @DisplayName("success case")
        public void successTest() {
            Stage stage = Stage.builder().build();
            given(stageRepository.getByLocationAndStep(anyString(), anyString()))
                    .willReturn(Optional.of(stage));

            stageAdapter.getByLocationAndStep("10","20");

            then(stageRepository).should(times(1))
                    .getByLocationAndStep(anyString(), anyString());
        }
    }
}

