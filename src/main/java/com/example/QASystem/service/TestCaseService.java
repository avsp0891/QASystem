package com.example.QASystem.service;


import com.example.QASystem.exceptions.ProjectNotFoundException;
import com.example.QASystem.exceptions.TestCaseNotFoundException;
import com.example.QASystem.model.Project;
import com.example.QASystem.model.Step;
import com.example.QASystem.model.TestCase;
import com.example.QASystem.model.dtos.TestCaseDto;
import com.example.QASystem.repositories.ProjectRepository;
import com.example.QASystem.repositories.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        log.info("Получение тест-кейса по id");
        TestCase testCase = repository.findById(id).orElseThrow(() -> new TestCaseNotFoundException(id));
        testCase.getSteps().sort(Comparator.comparingLong(Step::getNumber));
        return TestCaseDto.getDto(testCase);
    }

    public List<TestCaseDto> getAllByProjectId(Integer projectId) {
        log.info("Получение всех тест-кейсов проекта с id - '" + projectId + "'");
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        List<TestCase> testCases = repository.getAllByProjectId(projectId);
        for (TestCase t: testCases){
            t.getSteps().sort(Comparator.comparingLong(Step::getNumber));
        }
        return TestCaseDto.getListDto(testCases);
    }

    public TestCaseDto create(Integer projectId, TestCase testCase) {
        log.info("Создание тест-кейса");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        testCase.setProject(project);
        Long num = 1L;
        for (Step s : testCase.getSteps()) {
            s.setTestCase(testCase);
            s.setNumber(num);
            num++;
        }
        TestCase testCaseCreated = repository.save(testCase);
        return TestCaseDto.getDto(testCaseCreated);
    }

    public TestCaseDto edit(Integer projectId, Integer testCaseId, TestCase newTestCase) {
        log.info("Редактирование тест-кейса с id='" + testCaseId + "'");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        TestCase testCase = repository.findById(testCaseId).orElseThrow(() -> new TestCaseNotFoundException(testCaseId));
        if (!testCase.getProject().getId().equals(project.getId())) {
            throw new RuntimeException("id проекта - ' " + project.getId() + "' не соответствует id тест-кейса - ' " + testCase.getProject().getId() + "'");
        }
        testCase.getSteps().clear();
        testCase.setTestcaseName(newTestCase.getTestcaseName());
        testCase.setTestcaseDescription(newTestCase.getTestcaseDescription());
        Long num = 1L;
        for (Step s : newTestCase.getSteps()) {
            s.setTestCase(testCase);
            testCase.getSteps().add(s);
            s.setNumber(num);
            num++;
        }
        TestCase testCaseEdited = repository.save(testCase);
        return TestCaseDto.getDto(testCaseEdited);
    }

    public TestCaseDto delete(Integer id) {
        log.info("Удаление тест-кейса с id='" + id + "'");
        TestCase testCase = repository.findById(id).orElseThrow(() -> new TestCaseNotFoundException(id));
        repository.deleteById(Math.toIntExact(testCase.getId()));
        return TestCaseDto.getDto(testCase);
    }


}
