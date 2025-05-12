package com.example.QASystem.repositories;


import com.example.QASystem.model.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    List<CheckList> getAllByProjectId(Long id);

}

