package com.workingout.workingout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workingout.workingout.model.Exercise;

@Repository
public interface ExercisesRepository extends JpaRepository<Exercise, Long>{
    
}
