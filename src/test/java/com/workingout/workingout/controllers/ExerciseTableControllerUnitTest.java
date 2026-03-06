package com.workingout.workingout.controllers;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.models.Exercise;
import com.workingout.workingout.repository.UsersRepository;
import com.workingout.workingout.service.ExerciseTableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseTableController.class)
public class ExerciseTableControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ExerciseTableService tableService;
    @MockitoBean
    private UsersRepository usersRepository;
    @Test
    void shouldReturnMainPage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    @Test
    void shouldReturnExercisesForWednesday() throws Exception {
        DayOfWeek testDay = DayOfWeek.WEDNESDAY;
        List<Exercise> mockExercises = List.of(new Exercise());

        when(tableService.getExercisesFromDay(testDay)).thenReturn(mockExercises);

        mockMvc.perform(get("/exercises")
                        .param("day", "WEDNESDAY"))
                .andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable"))
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attribute("day", testDay))
                .andExpect(model().attribute("exercises", mockExercises))
                .andExpect(model().attributeExists("newExercise"));

        verify(tableService).getExercisesFromDay(testDay);
    }
    @Test
    void shouldReturnAllExercisesForDayNull() throws Exception{
        List<Exercise> mockExercises = List.of(new Exercise());

        when(tableService.getAllExercises()).thenReturn(mockExercises);

        mockMvc.perform(get("/exercises")
                        .param("day", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable"))
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attribute("exercises", mockExercises))
                .andExpect(model().attributeExists("newExercise"));
        verify(tableService).getAllExercises();
    }
    @Test
    void shouldAddExerciseForUserOne() throws Exception{
        mockMvc.perform(post("/exercises/add")
                        .param("name", "Push ups")
                        .param("sets", "5")
                        .param("reps", "20")
                        .param("day", "WEDNESDAY")
                        .param("extraInfo", "Keep form"))

                .andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable"))

                .andExpect(model().attributeExists("exercises"));

        verify(tableService).addExercise(argThat(exercise ->
                exercise.getName().equals("Push ups") &&
                        exercise.getSets() == 5 &&
                        exercise.getDay() == DayOfWeek.WEDNESDAY
        ));
    }
    @Test
    void shouldThrowExceptionWhenBindingResultHasError() throws Exception{
        mockMvc.perform(post("/exercises/add")
                .param("name","gg")
                .param("sets", "1")
                .param("reps","3")
                .param("day","WEDNESDAY")
                .param("extraInfo","Keep form"))
                .andExpect(status().isNotAcceptable());
    }
    @Test
    void shouldDeleteAllExercisesFromMonday() throws Exception{
        DayOfWeek testDay = DayOfWeek.MONDAY;
        mockMvc.perform(delete("/exercises/removeall")
                .param("day","MONDAY")
                ).andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable")
                );

        verify(tableService).deleteAllExercisesFromDay(argThat(dayOfWeek ->
                dayOfWeek == testDay));
    }
    @Test
    void shouldDeleteExerciseByIdTen() throws Exception{
        Long idToDelete = 10L;
        mockMvc.perform(delete("/exercises/remove/{id}", idToDelete)
                ).andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable"));
        verify(tableService).deleteExerciseById(idToDelete);
    }
}
