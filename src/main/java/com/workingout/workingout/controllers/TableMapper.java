package com.workingout.workingout.controllers;

import com.workingout.workingout.dto.ExerciseDTO;
import com.workingout.workingout.exceptions.InputNotValidException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.repository.UsersRepository;
import com.workingout.workingout.service.ExerciseTableService;

@Controller
public class TableMapper {
    private final ExerciseTableService tableService;
    //TODO:Na chwile zeby dodac na sztywno userId, pozniej zmienic
    private final UsersRepository usersRepository;

    public TableMapper(ExerciseTableService tableService,
                       UsersRepository usersRepository){
        this.tableService = tableService;
        this.usersRepository = usersRepository;
    }
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    private void prepareExercise(DayOfWeek day, Model model){
        ExerciseDTO newExercise = new ExerciseDTO();
        newExercise.setDay(day);
        if(day != null){
            model.addAttribute("exercises", tableService.getExercisesFromDay(day));
            model.addAttribute("day", day);
        }else{
            model.addAttribute("exercises", tableService.getAllExercises());
        }
        model.addAttribute("newExercise", newExercise);
    }
    @GetMapping("/exercises")
    public String getExercisesFromDay(@RequestParam DayOfWeek day, Model model){
        
        prepareExercise(day, model);
        return "exercisetable :: exerciseTable";
    }
    @PostMapping("/exercises/add")
    public String addExercise(@Valid ExerciseDTO newExercise, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            throw new InputNotValidException();
        }
        newExercise.setUserId(1L);
        tableService.addExercise(newExercise);
        prepareExercise(newExercise.getDay(), model);
        return "exercisetable :: exerciseTable";
    }
}
