package com.workingout.workingout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String getAllExercises(Model model){
        model.addAttribute("exercises", tableService.getAllExercises());
        return "exercisetable :: exerciseTable";
    }
}
