package com.workingout.workingout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workingout.workingout.model.DayOfWeek;
import com.workingout.workingout.service.ExerciseTableService;

@Controller
public class TableMapper {
    private final ExerciseTableService tableService;

    public TableMapper(ExerciseTableService tableService){
        this.tableService = tableService;
    }
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/exercises")
    public String getExercisesFromDay(@RequestParam(name = "dayNumber", required = false) DayOfWeek day, Model model){
        
        if(day != null){
            model.addAttribute("exercises", tableService.getExercisesFromDay(day));
        }else{
            model.addAttribute("exercises", tableService.getAllExercises());
        }
        
        return "exercisetable :: exerciseTable";
    }
}
