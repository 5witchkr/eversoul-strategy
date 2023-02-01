package com.strategy.adapter.outbound.persistence;

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
    private Long lastBatch;

    @Column //ex 14_24 -> 14와 같이한 24
    private String connectSouls;

    @Column
    private int connectCount;

    public void addCount() {
        this.connectCount++;
    }
}
