package com.workingout.workingout.controllers;

import com.workingout.workingout.exceptions.GlobalExceptionHandler;
import com.workingout.workingout.exceptions.InputNotValidException;
import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.models.Exercise;
import com.workingout.workingout.repository.UsersRepository;
import com.workingout.workingout.service.ExerciseTableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest({ExerciseTableController.class, GlobalExceptionHandler.class})
public class ExerciseTableControllerUnitTest {

     class PrincipalImpl implements Principal{
        @Override
        public String getName(){
            return "John";
        }
    }
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ExerciseTableService tableService;
    @MockitoBean
    private UsersRepository usersRepository;
    private Principal user;
    @BeforeEach
    void setUp(){
        user = new PrincipalImpl();
    }
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

        when(tableService.getExercisesFromDayWithUserId(testDay, user)).thenReturn(mockExercises);

        mockMvc.perform(get("/exercises")
                        .principal(user)
                        .param("day", "WEDNESDAY"))
                .andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable"))
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attribute("day", testDay))
                .andExpect(model().attribute("exercises", mockExercises))
                .andExpect(model().attributeExists("newExercise"));

        verify(tableService).getExercisesFromDayWithUserId(testDay,user);
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


        mockMvc.perform(post("/exercises")
                        .principal(user)
                        .param("name", "Push ups")
                        .param("sets", "5")
                        .param("reps", "20")
                        .param("day", "WEDNESDAY")
                        .param("extraInfo", "Keep form"))

                .andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable"))
                .andExpect(model().attributeExists("exercises"));

        verify(tableService).addExercise(argThat(exercise->
                exercise.getName().equals("Push ups") &&
                        exercise.getSets() == 5 &&
                        exercise.getDay() == DayOfWeek.WEDNESDAY
        ),argThat(user -> Objects.equals(user.getName(), "John")));
    }
    @Test
    void shouldThrowExceptionWhenBindingResultHasError() throws Exception{
        mockMvc.perform(post("/exercises")
                .param("name","")
                .param("sets", "1")
                .param("reps","3")
                .param("day","WEDNESDAY")
                .param("extraInfo","Keep form"))
                .andExpect(status().isBadRequest());

    }
    @Test
    void shouldDeleteAllExercisesFromMonday() throws Exception{
        DayOfWeek testDay = DayOfWeek.MONDAY;
        mockMvc.perform(delete("/exercises")
                        .principal(user)
                .param("day","MONDAY")
                ).andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable")
                );

        verify(tableService).deleteAllExercisesFromDay(argThat(dayOfWeek ->
                dayOfWeek == testDay), argThat(user -> user.getName().equals("John")));
    }
    @Test
    void shouldDeleteExerciseByIdTen() throws Exception{
        Long idToDelete = 10L;
        mockMvc.perform(delete("/exercises/{id}", idToDelete)
                ).andExpect(status().isOk())
                .andExpect(view().name("exercisetable :: exerciseTable"));
        verify(tableService).deleteExerciseById(idToDelete);
    }
}
