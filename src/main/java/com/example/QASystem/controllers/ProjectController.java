package com.example.QASystem.controllers;


import com.example.QASystem.model.Project;
import com.example.QASystem.model.dtos.ProjectDto;
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
    public List<ProjectDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/project/{id}")
    public ProjectDto getById(@PathVariable("id") Integer id){
        return service.getById(id);
    }


    @PostMapping("/project")
    public ProjectDto create(@RequestBody Project project){
        return service.create(project);
    }

    @DeleteMapping("/project/{id}")
    public ProjectDto delete(@PathVariable("id") Integer id){
        return service.delete(id);
    }

}
