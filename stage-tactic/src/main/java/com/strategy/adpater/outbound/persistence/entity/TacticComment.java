package com.strategy.adpater.outbound.persistence.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TacticComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tactic_id", referencedColumnName = "id")
    private Tactic tactic;


    @Builder
    public TacticComment(String username, String contents, Tactic tactic){
        this.username = username;
        this.contents = contents;
        this.tactic = tactic;
    }
}
