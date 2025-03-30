package com.example.QASystem.controllers;


import com.example.QASystem.model.CheckList;
import com.example.QASystem.model.TestCase;
import com.example.QASystem.model.dtos.CheckListDto;
import com.example.QASystem.model.dtos.TestCaseDto;
import com.example.QASystem.service.CheckListService;
import com.example.QASystem.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
public class CheckListController {

    private final CheckListService service;

    @GetMapping("/checklist")
    public List<CheckListDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/checklist/{id}")
    public CheckListDto getById(@PathVariable("id") Integer id){
        return service.getById(id);
    }

    @GetMapping("/project/{project_id}/checklist")
    public List<CheckListDto> getAllByProjectId(@PathVariable("project_id") Integer id){
        return service.getAllByProjectId(id);
    }

    @PostMapping("/project/{project_id}/checklist")
    public CheckListDto create(@PathVariable("project_id") Integer id, @RequestBody CheckList checkList){
        return service.create(id, checkList);
    }

    @PutMapping("/project/{project_id}/checklist/{checklist_id}")
    public CheckListDto edit(@PathVariable("project_id") Integer projectId, @PathVariable("checklist_id") Integer checkListId, @RequestBody CheckList checkList){
        return service.edit(projectId, checkListId, checkList);
    }

    @DeleteMapping("/checklist/{id}")
    public CheckListDto delete(@PathVariable("id") Integer id){
        return service.delete(id);
    }

}
