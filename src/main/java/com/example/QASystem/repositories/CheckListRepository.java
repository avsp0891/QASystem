package com.example.QASystem.repositories;


import com.example.QASystem.model.CheckList;
import com.example.QASystem.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList, Integer> {

    List<CheckList> getAllByProjectId(Integer id);

}

