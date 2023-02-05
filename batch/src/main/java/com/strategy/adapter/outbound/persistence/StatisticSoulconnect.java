package com.strategy.adapter.outbound.persistence;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class StatisticSoulconnect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long connectSoul;

    @Column
    private Long connectedSoul;

    @Column
    private int connectCount;

    @Builder
    public StatisticSoulconnect(Long connectSoul, Long connectedSoul){
        this.connectSoul = connectSoul;
        this.connectedSoul = connectedSoul;
    }

    public void addCount() {
        this.connectCount++;
    }
}
