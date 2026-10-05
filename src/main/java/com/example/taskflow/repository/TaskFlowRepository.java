package com.example.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskflow.model.TaskFlowEntity;

public interface TaskFlowRepository extends JpaRepository<TaskFlowEntity,Long>{
	
}