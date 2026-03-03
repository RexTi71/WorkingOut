package com.workingout.workingout.controllers;

import com.workingout.workingout.dto.ExerciseDTO;
import com.workingout.workingout.exceptions.InputNotValidException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.repository.UsersRepository;
import com.workingout.workingout.service.ExerciseTableService;

@Controller
public class ExerciseTableController {
    private final ExerciseTableService tableService;
    //TODO:Na chwile zeby dodac na sztywno userId, pozniej zmienic

    public ExerciseTableController(ExerciseTableService tableService,
                                   UsersRepository usersRepository){
        this.tableService = tableService;
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
    @DeleteMapping("/exercises/removeall")
    public String deleteAllExercisesFromDay(@RequestParam DayOfWeek day, Model model){
        prepareExercise(day, model);
        tableService.deleteAllExercisesFromDay(day);
        //Pozniej na popup boxa zmienic
        //Dodaje nie potrzebnie cala tabele
        return "exercisetable :: exerciseTable";
    }
    @DeleteMapping("/exercises/remove/{id}")
    public String deleteExerciseById(@PathVariable Long id){
        tableService.deleteExerciseById(id);
        return "exercisetable :: exerciseTable";
    }
}
