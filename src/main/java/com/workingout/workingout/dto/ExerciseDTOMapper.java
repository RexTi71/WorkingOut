package com.workingout.workingout.dto;

import com.workingout.workingout.models.User;
import org.springframework.stereotype.Component;

import com.workingout.workingout.models.Exercise;

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
    public Exercise toEntity(ExerciseDTO exerciseDTO, User user){
         return new Exercise(exerciseDTO.getName(),
                                    exerciseDTO.getSets(),
                                    exerciseDTO.getReps(),
                                    exerciseDTO.getExtraInfo(),
                                    exerciseDTO.getDay(),
                                    user);
    }
}
