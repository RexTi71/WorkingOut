package com.workingout.workingout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workingout.workingout.model.DayOfWeek;
import com.workingout.workingout.model.Exercise;
import com.workingout.workingout.repository.UsersRepository;
import com.workingout.workingout.service.ExerciseTableService;

@Controller
public class TableMapper {
    private final ExerciseTableService tableService;

    //TODO:Na chwile zeby dodac na sztywno userId, pozniej zmienic
    private final UsersRepository usersRepository;
    public TableMapper(ExerciseTableService tableService, UsersRepository usersRepository){
        this.tableService = tableService;
        this.usersRepository = usersRepository;
    }
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    private void prepareExercise(DayOfWeek day, Model model){
        Exercise newExercise = new Exercise();
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
    public String addExercise(@ModelAttribute("newExercise") Exercise newExercise, Model model){
        //TODO:Do zmiany pozniej
        newExercise.setUser(usersRepository.getReferenceById(1L));
        tableService.addExercise(newExercise);
        prepareExercise(newExercise.getDay(), model);
        return "exercisetable :: exerciseTable";
    }
}
