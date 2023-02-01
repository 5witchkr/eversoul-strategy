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
public class SoulconnectBatchdata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tacticAndSoul; //ex tactic 1, soul 4  -> 1-4

    @Column
    private Long lastBatch;

    @Builder
    public SoulconnectBatchdata(String tacticAndSoul){
        this.tacticAndSoul = tacticAndSoul;
    }
}
