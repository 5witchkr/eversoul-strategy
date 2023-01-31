package com.strategy;


import javax.persistence.*;

@Entity
public class StatisticPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long lastBatch;

    @Column
    private String positionName;

    @Column
    private int positionCount;

}
