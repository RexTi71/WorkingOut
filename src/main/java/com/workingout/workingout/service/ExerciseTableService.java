package com.workingout.workingout.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.workingout.workingout.model.DayOfWeek;
import com.workingout.workingout.model.Exercise;
import com.workingout.workingout.repository.ExercisesRepository;

@Service
public class ExerciseTableService {
    private final ExercisesRepository exerciseRepository;

    public ExerciseTableService(ExercisesRepository exercisesRepository){
        this.exerciseRepository = exercisesRepository;
    }
    
    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
    public List<Exercise> getExercisesFromDay(DayOfWeek day){
        return exerciseRepository.findByDay(day);
    }
}
