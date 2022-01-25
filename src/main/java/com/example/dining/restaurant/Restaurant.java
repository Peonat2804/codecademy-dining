package com.example.dining.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue
    @Setter @Getter
    private Long id;

    @Column(name="PEANUT")
    @Setter @Getter
    private Float peanutScore;

    @Column(name="EGG")
    @Setter @Getter
    private Float eggScore;

    @Column(name="DIARY")
    @Setter @Getter
    private Float diaryScore;

    @Column(name="SCORE")
    @Setter @Getter
    private Float score;

    public Restaurant() {}
}
