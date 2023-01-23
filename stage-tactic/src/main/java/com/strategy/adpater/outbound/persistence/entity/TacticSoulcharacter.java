package com.strategy.adpater.outbound.persistence.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TacticSoulcharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String tier;

    @OneToMany(mappedBy = "tacticSoulcharacter", cascade = CascadeType.ALL)
    private List<TacticCharacter> tacticCharacters = new ArrayList<>();

    @Builder
    public TacticSoulcharacter(Long id, String name, String type, String tier) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tier = tier;
    }
}
