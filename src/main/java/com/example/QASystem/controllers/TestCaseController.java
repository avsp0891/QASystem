package com.example.QASystem.controllers;


import com.example.QASystem.model.dtos.TestCaseDto;
import com.example.QASystem.model.dtos.TestCaseRequest;
import com.example.QASystem.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
public class TestCaseController {

    private final TestCaseService service;

    @GetMapping("/testcase")
    public List<TestCaseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/testcase/{id}")
    public TestCaseDto getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/project/{project_id}/testcase")
    public List<TestCaseDto> getAllByProjectId(@PathVariable("project_id") Long id) {
        return service.getAllByProjectId(id);
    }


    @PostMapping("/project/{project_id}/testcase")
    public TestCaseDto create(@PathVariable("project_id") Long id, @RequestBody TestCaseRequest request) {
        return service.create(id, request);
    }

    @PutMapping("/project/{project_id}/testcase/{test_case_id}")
    public TestCaseDto edit(@PathVariable("project_id") Long projectId, @PathVariable("test_case_id") Long testCaseId, @RequestBody TestCaseRequest request) {
        return service.edit(projectId, testCaseId, request);
    }

    @DeleteMapping("/testcase/{id}")
    public TestCaseDto delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

}
