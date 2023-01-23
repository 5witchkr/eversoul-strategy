package com.strategy.adapter.outbound.persistence;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SoulCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String tier;

    @Builder
    public SoulCharacter(Long id, String name, String type, String tier) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tier = tier;
    }

}
