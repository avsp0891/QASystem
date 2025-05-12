package com.example.QASystem.controllers;


import com.example.QASystem.model.dtos.ProjectDto;
import com.example.QASystem.model.dtos.ProjectRequest;
import com.example.QASystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService service;

    @GetMapping("/project")
    public List<ProjectDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/project/{id}")
    public ProjectDto getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }


    @PostMapping("/project")
    public ProjectDto create(@RequestBody ProjectRequest request) {
        return service.create(request);
    }

    @PutMapping("/project/{id}")
    public ProjectDto edit(@PathVariable("id") Long id, @RequestBody ProjectRequest request) {
        return service.edit(request, id);
    }

    @DeleteMapping("/project/{id}")
    public ProjectDto delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

}
