package com.workingout.workingout.repository;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workingout.workingout.models.DayOfWeek;
import com.workingout.workingout.models.Exercise;

@Repository
public interface ExercisesRepository extends JpaRepository<Exercise, Long>{
    List<Exercise> findByDayAndUserId(DayOfWeek day, Long userId);
    @Transactional
    void deleteAllByDayAndUserId(DayOfWeek day, Long userId);
}
