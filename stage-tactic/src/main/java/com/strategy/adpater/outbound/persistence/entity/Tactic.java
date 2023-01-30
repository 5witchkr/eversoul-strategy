package com.strategy.adpater.outbound.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tactic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String position;

    @Column
    private int power;

    @Column
    private String info = "";

    @Column
    private String title;


    @Column
    private int recommendCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;

    @OneToMany(mappedBy = "tactic", cascade = CascadeType.ALL)
    private List<TacticCharacter> tacticCharacters = new ArrayList<>();


    @OneToMany(mappedBy = "tactic", cascade = CascadeType.ALL)
    private List<TacticComment> tacticComments = new ArrayList<>();

    @Builder
    public Tactic(String position, int power, String info, String title, Stage stage, List<TacticCharacter> tacticCharacters) {
        this.position = position;
        this.power = power;
        this.title = title;
        this.info = info;
        this.stage = stage;
        this.tacticCharacters = tacticCharacters;
    }
}
