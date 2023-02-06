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
public class StatisticSoulselect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private Long lastBatch;

    @Column
    private int selectCount;


    public void addCount() {
        this.selectCount++;
    }

    @Builder
    public StatisticSoulselect(Long id, Long lastBatch, int selectCount) {
        this.id = id;
        this.lastBatch = lastBatch;
        this.selectCount = selectCount;
    }

}
