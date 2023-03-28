package com.Group.ChangingE.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    @ManyToOne
    @JoinColumn(name = "School_id")
    private School school;
}
