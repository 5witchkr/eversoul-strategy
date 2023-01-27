package com.strategy.adapter.outbound.persistence.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StorySoulcharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String tier;


    //oneToMany Episode
    @OneToMany(mappedBy = "storySoulcharacter", cascade = CascadeType.ALL)
    private List<StoryEpisode> storyEpisodes = new ArrayList<>();

    @Builder
    public StorySoulcharacter(Long id, String name, String type, String tier) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tier = tier;
    }

}
