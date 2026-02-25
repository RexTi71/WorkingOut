package com.workingout.workingout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.models.Exercise;

@Repository
public interface ExercisesRepository extends JpaRepository<Exercise, Long>{
    List<Exercise> findByDay(DayOfWeek day);
}
