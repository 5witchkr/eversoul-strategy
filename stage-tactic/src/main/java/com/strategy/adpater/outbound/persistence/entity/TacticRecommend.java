package com.strategy.adpater.outbound.persistence.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TacticRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int score = 0;

    @OneToOne(mappedBy = "tacticRecommend")
    private Tactic tactic;
}
