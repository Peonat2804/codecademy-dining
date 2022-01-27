package com.example.dining.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name="restaurant")
@Table(name="restaurant")
@IdClass(RestaurantId.class)
@Setter @Getter
public class Restaurant {
    @Column(name="ID", columnDefinition="bigint auto_increment")
    private Long id;

    @Id
    @Column(name="NAME", length=20)
    private String name;

    @Column(name="CITY", length=20)
    private String city;

    @Column(name="STATE", length=20)
    private String state;

    @Id
    @Column(name="ZIP", length=20)
    private String zip;

    @Column(name="PEANUT")
    private Float peanutScore;

    @Column(name="EGG")
    private Float eggScore;

    @Column(name="DIARY")
    private Float diaryScore;

    @Column(name="SCORE")
    private Float score;

    public Restaurant() {}

    public Restaurant(String name, String zip) {
        this.name = name;
        this.zip = zip;
    }

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
