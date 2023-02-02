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
    private Long id;    //tacticId

    @Column
    private Long soul1;

    @Column
    private Long soul2;

    @Column
    private Long soul3;

    @Column
    private Long soul4;

    @Column
    private Long soul5;

    @Column
    private Long lastBatch;


    @Builder
    public SoulconnectBatchdata(Long id){
        this.id = id;
    }

    public void addSoulId(Long soulId){
        if (this.soul1 == null){
            this.soul1 = soulId;
            return;
        }
        if (this.soul2 == null){
            this.soul2 = soulId;
        }
        if (this.soul3 == null){
            this.soul3 = soulId;
        }
        if (this.soul4 == null){
            this.soul4 = soulId;
        }
        if (this.soul5 == null){
            this.soul5 = soulId;
        }
    }
}
