package com.workingout.workingout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workingout.workingout.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{
    
}
