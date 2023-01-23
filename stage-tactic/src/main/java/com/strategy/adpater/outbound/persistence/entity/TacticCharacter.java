package com.strategy.adpater.outbound.persistence.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TacticCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tactic_id", referencedColumnName = "id")
    private Tactic tactic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tacticSoulcharacter_id", referencedColumnName = "id")
    private TacticSoulcharacter tacticSoulcharacter;

    @Builder
    public TacticCharacter(String level, Tactic tactic, TacticSoulcharacter tacticSoulcharacter) {
        this.level = level;
        this.tactic = tactic;
        this.tacticSoulcharacter = tacticSoulcharacter;
    }
}
