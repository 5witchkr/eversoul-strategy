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
    private Long tacticId;

    @Column
    private Long soulId;

    @Column
    private int isBatched;

    @Column
    private Long lastBatch;

    @Builder
    public SoulconnectBatchdata(Long tacticId, Long soulId){
        this.tacticId = tacticId;
        this.soulId = soulId;
        this.isBatched = 0;
    }

    public SoulconnectBatchdata isBatchDone(){
        this.isBatched = 1;
        return this;
    }
}
