package com.example.dining.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name="restaurant")
@Table(name="restaurant")
@IdClass(RestaurantId.class)
public class Restaurant {
    @GeneratedValue
    @Setter @Getter
    private Long id;

    @Id @Getter @Setter
    @Column(name="NAME", length=20)
    private String name;

    @Column(name="CITY", length=20)
    @Getter @Setter
    private String city;

    @Column(name="STATE", length=20)
    @Getter @Setter
    private String state;

    @Id
    @Column(name="ZIP", length=20)
    @Getter @Setter
    private String zip;

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

    public Restaurant(String name, String city, String state, String zip,
        Float peanutScore, Float eggScore, Float diaryScore, Float score) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.diaryScore = diaryScore;
        this.score = score;
    }
}
