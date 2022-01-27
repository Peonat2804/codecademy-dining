package com.example.dining.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="REVIEW")
@Setter @Getter
public class Review {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="RES_ID", nullable=false)
    private Long restId;

    @Column(name="PEANUT", columnDefinition = "int check(PEANUT > 0 and PEANUT <= 5)")
    private Integer peanutScore;

    @Column(name="EGG", columnDefinition = "int check(EGG > 0 and EGG <= 5)")
    private Integer eggScore;

    @Column(name="DIARY", columnDefinition = "int check(DIARY > 0 and DIARY <= 5)")
    private Integer diaryScore;

    @Column(name="CMT", length=1000)
    private String commentary;

    @Column(name="STATUS", nullable=false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.UNSUBMITED;

    public Review() {}

    public Review(Long restId, Integer peanutScore,
            Integer eggScore, Integer diaryScore, String commentary) {
        this.restId = restId;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.diaryScore = diaryScore;
        this.commentary = commentary;
        this.status = ReviewStatus.PENDING;
    }
}
