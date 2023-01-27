package com.strategy.adapter.outbound.persistence.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoryAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int orderNumber;

    @Column
    private int bestAnswer;//0 = worst,  1 = best

    @Column
    private String title;

    @Column
    private String info;

    //manyToOne Question
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storyQuestion_id", referencedColumnName = "id")
    private StoryQuestion storyQuestion;

    @Builder
    public StoryAnswer(int orderNumber, int bestAnswer, String title, String info, StoryQuestion storyQuestion){
        this.orderNumber = orderNumber;
        this.bestAnswer = bestAnswer;
        this.title = title;
        this.info = info;
        this.storyQuestion = storyQuestion;
    }

}
