package com.strategy.adapter.outbound.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoryQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int orderNumber;

    @Column
    private String info;

    //manyToOne episode
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storyEpisode_id", referencedColumnName = "id")
    private StoryEpisode storyEpisode;

    //oneToMany answer
    @OneToMany(mappedBy = "storyQuestion", cascade = CascadeType.ALL)
    private List<StoryAnswer> storyAnswers = new ArrayList<>();

    @Builder
    public StoryQuestion(int orderNumber, String info, StoryEpisode storyEpisode){
        this.orderNumber = orderNumber;
        this.info = info;
        this.storyEpisode = storyEpisode;
    }
}
