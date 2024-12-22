package com.example.QASystem.service;


import com.example.QASystem.exceptions.ProjectNotFoundException;
import com.example.QASystem.model.Project;
import com.example.QASystem.model.dtos.ProjectDto;
import com.example.QASystem.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProjectService {

    private final ProjectRepository repository;


    public List<ProjectDto> getAll() {
        log.info("Получение списка всех проектов");
        return repository.findAll().stream().map(ProjectDto::getDto).collect(Collectors.toList());
    }

    public ProjectDto getById(Integer id) {
        log.info("Получение проекта по id");
        Project p = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        return ProjectDto.getDto(p);
    }

    public ProjectDto create(Project project) {
        log.info("Создание проекта");
        return ProjectDto.getDto(repository.save(project));
    }

    public ProjectDto edit(Project newProject, Integer id) {
        log.info("Редактирование проекта с id='" + id + "'");
        Project p = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        p.setProjectName(newProject.getProjectName());
        p.setProjectDescription(newProject.getProjectDescription());
        return ProjectDto.getDto(repository.save(p));
    }

    public ProjectDto delete(Integer id) {
        log.info("Удаление проекта с id='" + id + "'");
        Project project = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        repository.deleteById(Math.toIntExact(project.getId()));
        return ProjectDto.getDto(project);
    }


}
