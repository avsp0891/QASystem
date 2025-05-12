package com.example.QASystem.service;


import com.example.QASystem.exceptions.ProjectNotFoundException;
import com.example.QASystem.model.Project;
import com.example.QASystem.model.ProjectStatus;
import com.example.QASystem.model.dtos.ProjectDto;
import com.example.QASystem.model.dtos.ProjectRequest;
import com.example.QASystem.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public ProjectDto getById(Long id) {
        log.info("Получение проекта по id");
        Project p = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        return ProjectDto.getDto(p);
    }

    public ProjectDto create(ProjectRequest request) {
        log.info("Создание проекта");
        Project p = new Project();
        p.setProjectName(request.getProjectName());
        p.setProjectDescription(request.getProjectDescription());
        p.setProjectStatus(ProjectStatus.ACTIVE);
        return ProjectDto.getDto(repository.save(p));
    }

    public ProjectDto edit(ProjectRequest request, Long id) {
        log.info("Редактирование проекта с id='" + id + "'");
        Project p = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        if (request.getProjectName() != null) {
            p.setProjectName(request.getProjectName());
        }
        if (request.getProjectDescription() != null) {
            p.setProjectDescription(request.getProjectDescription());
        }
        if (request.getProjectStatus() != null) {
            p.setProjectStatus(ProjectStatus.valueOf(request.getProjectStatus()));
        }
        return ProjectDto.getDto(repository.save(p));
    }

    public ProjectDto delete(Long id) {
        log.info("Удаление проекта с id='" + id + "'");
        Project project = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        repository.deleteById(project.getId());
        return ProjectDto.getDto(project);
    }


}
