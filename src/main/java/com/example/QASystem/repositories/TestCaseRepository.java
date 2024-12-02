package com.example.QASystem.repositories;


import com.example.QASystem.model.Project;
import com.example.QASystem.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {

}

