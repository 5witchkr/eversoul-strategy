package com.strategy.adpater.outbound.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @Column
    private String step;

    @Column
    private String position;

    @Column
    private String power;

    @Column
    private String soulCharacters;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL)
    private List<Tactic> tactics = new ArrayList<>();

    @Builder
    public Stage(String location, String step, String position, String power, String soulCharacters, List<Tactic> tactics) {
        this.location = location;
        this.step = step;
        this.position = position;
        this.power = power;
        this.soulCharacters = soulCharacters;
        this.tactics = tactics;
    }
}
