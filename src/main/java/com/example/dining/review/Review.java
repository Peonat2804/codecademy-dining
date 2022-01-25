package com.example.dining.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

enum Status {UNSUBMIITED, PENDING, APPROVED}

@Entity
@Table(name="REVIEW")
public class Review {
    @Id @GeneratedValue
    @Setter @Getter
    private Long id;

    @Column(name="NAME", nullable=false)
    @Getter @Setter
    private String name;

    @Column(name="RES_ID", nullable=false)
    @Getter @Setter
    private Long restaurantId;

    @Column(name="PEANUT", columnDefinition = "int check(PEANUT > 0 and PEANUT <= 5)")
    @Getter @Setter
    private Integer peanutScore;

    @Column(name="EGG", columnDefinition = "int check(EGG > 0 and EGG <= 5)")
    @Getter @Setter
    private Integer eggScore;

    @Column(name="DIARY", columnDefinition = "int check(DIARY > 0 and DIARY <= 5)")
    @Getter @Setter
    private Integer diaryScore;

    @Column(name="CMT", length=1000)
    @Getter @Setter
    private String commentary;

    @Column(name="STATUS", nullable=false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNSUBMIITED;

    public Review() {}
}
