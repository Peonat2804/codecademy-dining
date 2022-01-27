package com.example.dining.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER")
@Getter @Setter
public class User {
    @Column(name="ID", columnDefinition="bigint auto_increment")
    private Long id;

    @Id
    @Column(name="NAME", nullable=false, length=100)
    private String name;

    @Column(name="CITY", length=20)
    private String city;

    @Column(name="STATE", length=20)
    private String state;

    @Column(name="ZIP", length=20)
    private String zip;

    @Column(name="PEANUT_ALLERGY")
    private Boolean peanutAllergy;

    @Column(name="EGG_ALLERGY")
    private Boolean eggAllergy;

    @Column(name="DIARY_ALLERGY")
    private Boolean diaryAllergy;

    public User() {}

    public User(String name) {
        this.name = name;
    }
}
