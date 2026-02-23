package com.workingout.workingout.dto;

import org.springframework.stereotype.Component;

import com.workingout.workingout.model.Exercise;

@Component
public class ExerciseDTOMapper {
    // public ExerciseDTO toDto(Exercise exercise){
    //     ExerciseDTO ex = new ExerciseDTO();
    //     ex.setName(exercise.getName());
    //     ex.setReps(exercise.getReps());
    //     ex.setSets(exercise.getSets());
    //     ex.setExtraInfo(exercise.getExtraInfo());
    //     ex.setUserId(ex);
    //     return ex;
    // }
    public Exercise toEntity(ExerciseDTO exerciseDTO){
        Exercise ex = new Exercise();
        return ex;
    }
}
