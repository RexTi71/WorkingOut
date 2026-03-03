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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseTableController.class)
public class ExerciseTableControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ExerciseTableService tableService;
    @MockitoBean
    private UsersRepository usersRepository;
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
}
