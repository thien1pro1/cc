package com.Group.ChangingE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent")
    private Long parent;

    @Column(name = "level")
    private int level;

    @Column(name = "max_score")
    private int maxScore;

    @Column(name = "obligatory")
    private boolean obligatory;

    @OneToMany(mappedBy = "criteria")
    private List<Score> scores;

}
