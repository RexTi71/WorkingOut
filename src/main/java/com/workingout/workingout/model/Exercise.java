package com.workingout.workingout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private Integer sets;
    @Column(nullable=false)
    private Integer reps;
    @Column(nullable=false, columnDefinition = "TEXT")
    private String extraInfo;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private DayOfWeek day;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Exercise(){}
    public Exercise(String name, Integer sets, Integer reps, String extraInfo, DayOfWeek day, User user)
    {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.extraInfo = extraInfo;
        this.day = day;
        this.user = user;
    }
    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getSets(){
        return this.sets;
    }
    public void setSets(Integer sets){
        this.sets = sets;
    }
    public Integer getReps(){
        return this.reps;
    }
    public void setReps(Integer reps){
        this.reps = reps;
    }
    public String getExtraInfo(){
        return this.extraInfo;
    }
    public void setExtraInfo(String extraInfo){
        this.extraInfo = extraInfo;
    }
    public DayOfWeek getDay(){
        return this.day;
    }
    public void setDay(DayOfWeek day){
        this.day = day;
    }
    public User getUser(){
        return this.user;
    }
    public void setUser(User userId){
        this.user = userId;
    }
}
