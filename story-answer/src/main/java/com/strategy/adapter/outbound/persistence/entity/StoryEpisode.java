package com.strategy.adapter.outbound.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoryEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int orderNumber;

    @Column
    private String title;

    @Column
    private String info;

    //manyToOne soul
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storySoulcharacter_id", referencedColumnName = "id")
    private StorySoulcharacter storySoulcharacter;

    //oneToMany question
    @OneToMany(mappedBy = "storyEpisode", cascade = CascadeType.ALL)
    private List<StoryQuestion> storyQuestions = new ArrayList<>();


    @Builder
    public StoryEpisode(int orderNumber, String title, String info, StorySoulcharacter storySoulcharacter){
        this.orderNumber = orderNumber;
        this.title = title;
        this.info = info;
        this.storySoulcharacter = storySoulcharacter;
    }
}
