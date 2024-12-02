package com.example.QASystem.controllers;


import com.example.QASystem.model.Project;
import com.example.QASystem.model.TestCase;
import com.example.QASystem.model.dtos.TestCaseDto;
import com.example.QASystem.service.ProjectService;
import com.example.QASystem.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestCaseController {

    private final TestCaseService service;

    @GetMapping("/testcase")
    public List<TestCaseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/testcase/{id}")
    public TestCaseDto getById(@PathVariable("id") Integer id){
        return service.getById(id);
    }


    @PostMapping("/project/{project_id}/testcase")
    public TestCaseDto create(@PathVariable("project_id") Integer id, @RequestBody TestCase testCase){
        return service.create(id, testCase);
    }

    @DeleteMapping("/testcase/{id}")
    public TestCaseDto delete(@PathVariable("id") Integer id){
        return service.delete(id);
    }

}
