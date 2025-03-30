package com.example.QASystem.service;


import com.example.QASystem.exceptions.CheckListNotFoundException;
import com.example.QASystem.exceptions.ProjectNotFoundException;
import com.example.QASystem.model.*;
import com.example.QASystem.model.dtos.CheckListDto;
import com.example.QASystem.repositories.CheckListRepository;
import com.example.QASystem.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CheckListService {

    private final CheckListRepository repository;
    private final ProjectRepository projectRepository;


    public List<CheckListDto> getAll() {
        log.info("Получение списка всех чек-листов");
        return repository.findAll().stream().map(CheckListDto::getDto).collect(Collectors.toList());
    }

    public CheckListDto getById(Integer id) {
        log.info("Получение чек-листа по id");
        CheckList checkList = repository.findById(id).orElseThrow(() -> new CheckListNotFoundException(id));
        checkList.getItems().sort(Comparator.comparingLong(Item::getNumber));
        return CheckListDto.getDto(checkList);
    }

    public List<CheckListDto> getAllByProjectId(Integer projectId) {
        log.info("Получение всех чек-листов проекта с id - '" + projectId + "'");
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        List<CheckList> checkLists = repository.getAllByProjectId(projectId);
        for (CheckList checkList: checkLists){
            checkList.getItems().sort(Comparator.comparingLong(Item::getNumber));
        }
        return CheckListDto.getListDto(checkLists);
    }

    public CheckListDto create(Integer projectId, CheckList checkList) {
        log.info("Создание чек-листа");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        checkList.setProject(project);
        Long num = 1L;
        for (Item i : checkList.getItems()) {
            i.setCheckList(checkList);
            i.setNumber(num);
            num++;
        }
        CheckList checkListCreated = repository.save(checkList);
        return CheckListDto.getDto(checkListCreated);
    }

    public CheckListDto edit(Integer projectId, Integer checkListId, CheckList newCheckList) {
        log.info("Редактирование чек-листа с id='" + checkListId + "'");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        CheckList checkList = repository.findById(checkListId).orElseThrow(() -> new CheckListNotFoundException(checkListId));
        if (!checkList.getProject().getId().equals(project.getId())) {
            throw new RuntimeException("id проекта - ' " + project.getId() + "' не соответствует id чек-листа - ' " + checkList.getProject().getId() + "'");
        }
        checkList.getItems().clear();
        checkList.setCheckListName(newCheckList.getCheckListName());
        checkList.setCheckListDescription(newCheckList.getCheckListDescription());
        Long num = 1L;
        for (Item i : newCheckList.getItems()) {
            i.setCheckList(checkList);
            checkList.getItems().add(i);
            i.setNumber(num);
            num++;
        }
        CheckList checkListEdited = repository.save(checkList);
        return CheckListDto.getDto(checkListEdited);
    }

    public CheckListDto delete(Integer id) {
        log.info("Удаление чек-листа с id='" + id + "'");
        CheckList checkList = repository.findById(id).orElseThrow(() -> new CheckListNotFoundException(id));
        repository.deleteById(Math.toIntExact(checkList.getId()));
        return CheckListDto.getDto(checkList);
    }


}
