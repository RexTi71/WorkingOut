package com.workingout.workingout.service;

import java.security.Principal;
import java.util.List;

import com.workingout.workingout.dto.ExerciseDTO;
import com.workingout.workingout.dto.DTOMapper;
import com.workingout.workingout.models.User;
import org.springframework.stereotype.Service;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.models.Exercise;
import com.workingout.workingout.repository.ExercisesRepository;

@Service
public class ExerciseTableService {
    private final ExercisesRepository exerciseRepository;
    private final DTOMapper exerciseDTOMapper;
    private final UserLoginService userLoginService;
    public ExerciseTableService(ExercisesRepository exercisesRepository,
                                UserLoginService userLoginService,
                                DTOMapper exerciseDTOMapper){
        this.exerciseRepository = exercisesRepository;
        this.userLoginService = userLoginService;
        this.exerciseDTOMapper = exerciseDTOMapper;
    }
    
    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
    public List<Exercise> getExercisesFromDayWithUserId(DayOfWeek day, Principal userInfo){
        User user = getLogedUser(userInfo);
        return exerciseRepository.findByDayAndUserId(day, user.getId());
    }
    private User getLogedUser(Principal userInfo){
        return (User)userLoginService.loadUserByUsername(userInfo.getName());
    }
    public void addExercise(ExerciseDTO newExercise, Principal userInfo){
        User userRef = getLogedUser(userInfo);
        exerciseRepository.save(exerciseDTOMapper.toEntity(newExercise, userRef));
    }
    public void deleteAllExercisesFromDay(DayOfWeek day, Principal userInfo){
        User user = getLogedUser(userInfo);
        exerciseRepository.deleteAllByDayAndUserId(day, user.getId());
    }
    public void deleteExerciseById(Long id){
        exerciseRepository.deleteById(id);
    }
}
