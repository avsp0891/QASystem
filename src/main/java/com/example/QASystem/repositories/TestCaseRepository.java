package com.example.QASystem.repositories;


import com.example.QASystem.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    List<TestCase> getAllByProjectId(Long id);

}

