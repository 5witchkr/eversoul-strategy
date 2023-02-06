package com.strategy.adapter.outbound.persistence.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

    public void addCount() {
        this.positionCount++;
    }

    @Builder
    public StatisticPosition(Long lastBatch, String positionName, int positionCount) {
        this.lastBatch = lastBatch;
        this.positionName = positionName;
        this.positionCount = positionCount;
    }
}
