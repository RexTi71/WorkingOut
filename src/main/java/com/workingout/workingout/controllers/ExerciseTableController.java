package com.workingout.workingout.controllers;

import com.workingout.workingout.dto.ExerciseDTO;
import com.workingout.workingout.exceptions.InputNotValidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.repository.UsersRepository;
import com.workingout.workingout.service.ExerciseTableService;

import java.security.Principal;

@Controller
public class ExerciseTableController {
    private final ExerciseTableService tableService;

    public ExerciseTableController(ExerciseTableService tableService){
        this.tableService = tableService;
    }
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    private void prepareExercise(DayOfWeek day, Model model, Principal userInfo){
        ExerciseDTO newExercise = new ExerciseDTO();
        if(day != null){
            newExercise.setDay(day);
            model.addAttribute("exercises", tableService.getExercisesFromDayWithUserId(day, userInfo));
            model.addAttribute("day", day);
        }else{
            model.addAttribute("exercises", tableService.getAllExercises());
        }
        model.addAttribute("newExercise", newExercise);
    }
    @GetMapping("/exercises")
    public String getExercisesFromDay(@RequestParam(required = false) DayOfWeek day, HttpServletRequest request, Model model){

        prepareExercise(day, model, request.getUserPrincipal());
        return "exercisetable :: exerciseTable";
    }
    @PostMapping("/exercises")
    public String addExercise(@Valid ExerciseDTO newExercise,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              Model model){
        if(bindingResult.hasErrors()){
            throw new InputNotValidException();
        }
        Principal userPrincipal = request.getUserPrincipal();
        tableService.addExercise(newExercise, userPrincipal);
        prepareExercise(newExercise.getDay(), model, userPrincipal);
        return "exercisetable :: exerciseTable";
    }
    @DeleteMapping("/exercises")
    public String deleteAllExercisesFromDay(@RequestParam DayOfWeek day,
                                            HttpServletRequest request,
                                            Model model){
        Principal userPrincipal = request.getUserPrincipal();
        prepareExercise(day, model, userPrincipal);
        tableService.deleteAllExercisesFromDay(day, userPrincipal);
        return "exercisetable :: exerciseTable";
    }
    @DeleteMapping("/exercises/{id}")
    public String deleteExerciseById(@PathVariable Long id,
                                     HttpServletRequest request,
                                     Model model){
        tableService.deleteExerciseById(id);
        prepareExercise((DayOfWeek) model.getAttribute("day"), model, request.getUserPrincipal());
        return "exercisetable :: exerciseTable";
    }
}
