package com.strategy;

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

    @Builder
    public StatisticSoulselect(int selectCount){
        this.selectCount = 0;
    }

    public void addCount() {
        this.selectCount++;
    }
}
