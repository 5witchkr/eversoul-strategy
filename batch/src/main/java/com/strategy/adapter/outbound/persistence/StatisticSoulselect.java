package com.strategy.adapter.outbound.persistence;

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

}
