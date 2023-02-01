package com.strategy.adapter.outbound.persistence;

import javax.persistence.*;


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
}
