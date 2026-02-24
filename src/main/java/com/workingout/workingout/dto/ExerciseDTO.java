package com.workingout.workingout.dto;

import com.workingout.workingout.model.DayOfWeek;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ExerciseDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Write a name between 3 and 100 characters")
    private String name;
    @Min(value = 0, message = "Sets cannot be negative")
    private Integer sets;
    @Min(value = 0, message = "Reps cannot be negative")
    private Integer reps;
    private String extraInfo;
    private DayOfWeek day;
    private Long userId;

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
    public Long getUserId(){
        return this.userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
}
