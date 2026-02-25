package com.workingout.workingout.service;

import java.util.List;

import com.workingout.workingout.dto.ExerciseDTO;
import com.workingout.workingout.dto.ExerciseDTOMapper;
import com.workingout.workingout.models.User;
import com.workingout.workingout.repository.UsersRepository;
import org.springframework.stereotype.Service;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.models.Exercise;
import com.workingout.workingout.repository.ExercisesRepository;

@Service
public class ExerciseTableService {
    private final ExercisesRepository exerciseRepository;
    private final ExerciseDTOMapper exerciseDTOMapper;
    private final UsersRepository usersRepository;
    public ExerciseTableService(ExercisesRepository exercisesRepository,
                                UsersRepository usersRepository,
                                ExerciseDTOMapper exerciseDTOMapper){
        this.exerciseRepository = exercisesRepository;
        this.usersRepository = usersRepository;
        this.exerciseDTOMapper = exerciseDTOMapper;
    }
    
    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
    public List<Exercise> getExercisesFromDay(DayOfWeek day){
        return exerciseRepository.findByDay(day);
    }
    public void addExercise(ExerciseDTO newExercise){
        //TODO:Do zmiany pozniej
        User userRef = usersRepository.getReferenceById(newExercise.getUserId());
        exerciseRepository.save(exerciseDTOMapper.toEntity(newExercise, userRef));
    }
}
