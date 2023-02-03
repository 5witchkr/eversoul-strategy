package com.strategy.processor;


import com.strategy.application.port.outbound.RedisTacticRecommendOutboundPort;
import com.strategy.application.processor.TacticRecommendCheckProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TacticRecommendCheckProcessor Tests")
public class TacticRecommendCheckProcessorTests {

    private TacticRecommendCheckProcessor tacticRecommendCheckProcessor;
    private RedisTacticRecommendOutboundPort redisTacticRecommendOutboundPort;


    @TestFactory
    @DisplayName("addRecommend Tests")
    Stream<DynamicTest> addRecommend() {
        redisTacticRecommendOutboundPort = new TestRedisTacticRecommendOutboundPort();
        tacticRecommendCheckProcessor = new TacticRecommendCheckProcessor(redisTacticRecommendOutboundPort);

        String existsIp = "111.222.333.444";
        String notExistsIp = "222.333.444.555";
        String tacticId1 = "1";
        String tacticId2 = "2";
        String todayDate = "2023-01-30";
        String nextDayDate = "2023-02-01";

        return Stream.of(
                DynamicTest.dynamicTest("성공케이스1: userIp가 redis에 존재할경우 true 리턴", () -> {
                    boolean result = tacticRecommendCheckProcessor.checkExistIpAtTacticId(tacticId1, existsIp, todayDate);
                    assertThat(result).isInstanceOf(Boolean.class);
                    assertThat(result).isEqualTo(true);
                }),

                DynamicTest.dynamicTest("성공케이스2: userIp가 redis에 존재하지 않을 경우 false 리턴", () ->{
                    boolean result = tacticRecommendCheckProcessor.checkExistIpAtTacticId(tacticId1, notExistsIp, todayDate);
                    assertThat(result).isInstanceOf(Boolean.class);
                    assertThat(result).isEqualTo(false);
                }),
                DynamicTest.dynamicTest("성공케이스3: 유저1이 다음 날짜에 추천을 누른경우 false 리턴", () -> {
                    boolean result = tacticRecommendCheckProcessor.checkExistIpAtTacticId(tacticId1, existsIp, nextDayDate);
                    assertThat(result).isInstanceOf(Boolean.class);
                    assertThat(result).isEqualTo(false);
                }),
                DynamicTest.dynamicTest("성공케이스4: 유저2가 다음 날짜에 추천을 누른경우 false 리턴", () ->{
                    boolean result = tacticRecommendCheckProcessor.checkExistIpAtTacticId(tacticId1, notExistsIp, nextDayDate);
                    assertThat(result).isInstanceOf(Boolean.class);
                    assertThat(result).isEqualTo(false);
                }),
                DynamicTest.dynamicTest("성공케이스5: 유저1이 다른 게시글에 추천을 누른경우 false 리턴", () -> {
                    boolean result = tacticRecommendCheckProcessor.checkExistIpAtTacticId(tacticId2, existsIp, todayDate);
                    assertThat(result).isInstanceOf(Boolean.class);
                    assertThat(result).isEqualTo(false);
                }),
                DynamicTest.dynamicTest("성공케이스6: 유저2가 다른 게시글에 추천을 누른경우 false 리턴", () ->{
                    boolean result = tacticRecommendCheckProcessor.checkExistIpAtTacticId(tacticId2, notExistsIp, todayDate);
                    assertThat(result).isInstanceOf(Boolean.class);
                    assertThat(result).isEqualTo(false);
                })
        );
    }

    private static class TestRedisTacticRecommendOutboundPort implements RedisTacticRecommendOutboundPort {
        @Override
        public Set<String> getRecommendedIps(String key) {
            String existsIp = "111.222.333.444";
            String tacticId1 = "1";
            String todayDate = "2023-01-30";

            //1번 게시글은 오늘 날짜에 1명의 유저가 추천을 누른 상태
            if (Objects.equals(key, "tactic:" + tacticId1 + ":ips:" + todayDate)){
                return Set.of(existsIp);
            }

            return Set.of();
        }

        @Override
        public void incrementOpsForValue(String key) {

        }

        @Override
        public void setExpire(String key, int time, TimeUnit days) {

        }

        @Override
        public void addOpsForSet(String key, String userIp) {

        }
    }
}
