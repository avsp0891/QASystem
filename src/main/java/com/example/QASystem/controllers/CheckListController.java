package com.example.QASystem.controllers;


import com.example.QASystem.model.dtos.CheckListDto;
import com.example.QASystem.model.dtos.CheckListRequest;
import com.example.QASystem.service.CheckListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
public class CheckListController {

    private final CheckListService service;

    @GetMapping("/checklist")
    public List<CheckListDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/checklist/{id}")
    public CheckListDto getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/project/{project_id}/checklist")
    public List<CheckListDto> getAllByProjectId(@PathVariable("project_id") Long id) {
        return service.getAllByProjectId(id);
    }

    @PostMapping("/project/{project_id}/checklist")
    public CheckListDto create(@PathVariable("project_id") Long id, @RequestBody CheckListRequest request) {
        return service.create(id, request);
    }

    @PutMapping("/project/{project_id}/checklist/{checklist_id}")
    public CheckListDto edit(@PathVariable("project_id") Long projectId, @PathVariable("checklist_id") Long checkListId, @RequestBody CheckListRequest request) {
        return service.edit(projectId, checkListId, request);
    }

    @DeleteMapping("/checklist/{id}")
    public CheckListDto delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

}
