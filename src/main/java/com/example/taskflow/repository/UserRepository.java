package com.example.taskflow.repository;

import com.example.taskflow.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long>{
	
}