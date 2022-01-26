package com.example.dining.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER")
public class User {
    @GeneratedValue
    @Getter @Setter
    private Long id;

    @Id
    @Column(name="NAME", nullable=false, length=100)
    @Getter @Setter
    private String name;

    @Column(name="CITY", nullable=false, length=20)
    @Getter @Setter
    private String city;

    @Column(name="STATE", nullable=false, length=20)
    @Getter @Setter
    private String state;

    @Column(name="ZIP", nullable=false, length=20)
    @Getter @Setter
    private String zip;

    @Column(name="PEANUT_ALLERGY")
    @Getter @Setter
    private Boolean peanutAllergy;

    @Column(name="EGG_ALLERGY")
    @Getter @Setter
    private Boolean eggAllergy;

    @Column(name="DIARY_ALLERGY")
    @Getter @Setter
    private Boolean diaryAllergy;

    public User() {}

    public User(String name) {
        this.name = name;
    }
}
