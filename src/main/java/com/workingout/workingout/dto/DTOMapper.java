package com.workingout.workingout.dto;

import com.workingout.workingout.models.User;
import org.springframework.stereotype.Component;

import com.workingout.workingout.models.Exercise;

@Component
public class DTOMapper {
    public Exercise toEntity(ExerciseDTO exerciseDTO, User user){
         return new Exercise(exerciseDTO.getName(),
                                    exerciseDTO.getSets(),
                                    exerciseDTO.getReps(),
                                    exerciseDTO.getExtraInfo(),
                                    exerciseDTO.getDay(),
                                    user);
    }
    public User toEntity(UserDTO userDTO){
        return new User(
                userDTO.getUsername(),
                userDTO.getPassword()
        );
    }
}
