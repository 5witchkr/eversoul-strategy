package com.strategy.adapter.outbound;

import com.strategy.adpater.outbound.TacticAdapter;
import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.repository.TacticRepository;
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
@DisplayName("TacticAdapter Tests")
public class TacticAdapterTests {
    @Mock
    private TacticRepository tacticRepository;

    @InjectMocks
    private TacticAdapter tacticAdapter;

    @Nested
    @DisplayName("save 메소드 호출 테스트")
    class saveTest {
        @Test
        @DisplayName("success case")
        public void successTest() {
            Tactic tactic = Tactic.builder().build();
            given(tacticRepository.save(Mockito.any(Tactic.class)))
                    .willReturn(tactic);

            tacticAdapter.save(tactic);

            then(tacticRepository).should(times(1))
                    .save(Mockito.any(Tactic.class));
        }
    }
}
