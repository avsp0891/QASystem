package com.example.QASystem.service;


import com.example.QASystem.exceptions.CheckListNotFoundException;
import com.example.QASystem.exceptions.ProjectNotFoundException;
import com.example.QASystem.exceptions.TestCaseNotFoundException;
import com.example.QASystem.model.CheckList;
import com.example.QASystem.model.Item;
import com.example.QASystem.model.Project;
import com.example.QASystem.model.TestCase;
import com.example.QASystem.model.dtos.CheckListDto;
import com.example.QASystem.model.dtos.CheckListRequest;
import com.example.QASystem.model.dtos.ItemRequest;
import com.example.QASystem.repositories.CheckListRepository;
import com.example.QASystem.repositories.ProjectRepository;
import com.example.QASystem.repositories.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CheckListService {

    private final CheckListRepository repository;
    private final ProjectRepository projectRepository;
    private final TestCaseRepository testCaseRepository;


    public List<CheckListDto> getAll() {
        log.info("Получение списка всех чек-листов");
        return repository.findAll().stream().map(CheckListDto::getDto).collect(Collectors.toList());
    }

    public CheckListDto getById(Long id) {
        log.info("Получение чек-листа по id");
        CheckList checkList = repository.findById(id).orElseThrow(() -> new CheckListNotFoundException(id));
        checkList.getItems().sort(Comparator.comparingLong(Item::getNumber));
        return CheckListDto.getDto(checkList);
    }

    public List<CheckListDto> getAllByProjectId(Long projectId) {
        log.info("Получение всех чек-листов проекта с id - '" + projectId + "'");
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        List<CheckList> checkLists = repository.getAllByProjectId(projectId);
        for (CheckList checkList : checkLists) {
            checkList.getItems().sort(Comparator.comparingLong(Item::getNumber));
        }
        return CheckListDto.getListDto(checkLists);
    }

    public CheckListDto create(Long projectId, CheckListRequest request) {
        log.info("Создание чек-листа");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        CheckList checkList = new CheckList();
        checkList.setCheckListName(request.getCheckListName());
        checkList.setCheckListDescription(request.getCheckListDescription());
        checkList.setProject(project);
        checkList.setItems(new ArrayList<>());
        Long num = 1L;
        for (ItemRequest i : request.getItems()) {
            TestCase testCase = null;
            if (i.getTestCaseId() != null) {
                testCase = testCaseRepository.findById(i.getTestCaseId()).orElse(null);
            }
            Item item = new Item();
            item.setNumber(num);
            item.setItem(i.getItem());
            if (testCase != null) {
                item.setTestCaseId(testCase.getId());
            }
            item.setCheckList(checkList);
            checkList.getItems().add(item);
            num++;
        }
        CheckList checkListCreated = repository.save(checkList);
        return CheckListDto.getDto(checkListCreated);
    }

    public CheckListDto edit(Long projectId, Long checkListId, CheckListRequest request) {
        log.info("Редактирование чек-листа с id='" + checkListId + "'");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        CheckList checkList = repository.findById(checkListId).orElseThrow(() -> new CheckListNotFoundException(checkListId));
        if (!checkList.getProject().getId().equals(project.getId())) {
            throw new RuntimeException("id проекта - ' " + project.getId() + "' не соответствует id чек-листа - ' " + checkList.getProject().getId() + "'");
        }
        checkList.getItems().clear();
        checkList.setCheckListName(request.getCheckListName());
        checkList.setCheckListDescription(request.getCheckListDescription());
        Long num = 1L;
        for (ItemRequest i : request.getItems()) {
            TestCase testCase = testCaseRepository.findById(i.getTestCaseId()).orElseThrow(() -> new TestCaseNotFoundException(i.getTestCaseId()));
            Item item = new Item();
            item.setNumber(num);
            item.setItem(i.getItem());
            item.setTestCaseId(testCase.getId());
            item.setCheckList(checkList);
            num++;
        }
        CheckList checkListEdited = repository.save(checkList);
        return CheckListDto.getDto(checkListEdited);
    }

    public CheckListDto delete(Long id) {
        log.info("Удаление чек-листа с id='" + id + "'");
        CheckList checkList = repository.findById(id).orElseThrow(() -> new CheckListNotFoundException(id));
        repository.deleteById(checkList.getId());
        return CheckListDto.getDto(checkList);
    }


}
