package com.example.QASystem.service;


import com.example.QASystem.model.Project;
import com.example.QASystem.model.Step;
import com.example.QASystem.model.TestCase;
import com.example.QASystem.model.dtos.ProjectDto;
import com.example.QASystem.model.dtos.TestCaseDto;
import com.example.QASystem.repositories.ProjectRepository;
import com.example.QASystem.repositories.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TestCaseService {

    private final TestCaseRepository repository;
    private final ProjectRepository projectRepository;


    public List<TestCaseDto> getAll() {
        log.info("Получение списка всех тест-кейсов");
        return repository.findAll().stream().map(TestCaseDto::getDto).collect(Collectors.toList());
    }

    public TestCaseDto getById(Integer id) {
        log.info("Получение проекта по id");
        TestCase testCase = repository.findById(id).orElseThrow(() -> new RuntimeException("Тест-кейс с id='" + id + "' отсутствует"));
        return TestCaseDto.getDto(testCase);
    }

    public TestCaseDto create(Integer projectId, TestCase testCase) {
        log.info("Создание тест-кейса");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Проект с id='" + projectId + "' отсутствует"));
        testCase.setProject(project);
        for (Step s: testCase.getSteps()){
            s.setTestCase(testCase);
        }
        TestCase testCaseCreated = repository.save(testCase);
        return TestCaseDto.getDto(testCaseCreated);
    }

    public TestCaseDto delete(Integer id) {
        log.info("Удаление тест-кейса с id='" + id + "'");
        TestCase testCase = repository.findById(id).orElseThrow(() -> new RuntimeException("Тест-кейс с id='" + id + "' отсутствует"));
        repository.deleteById(Math.toIntExact(testCase.getId()));
        return TestCaseDto.getDto(testCase);
    }


}
