package com.example.QASystem.model.dtos;


import com.example.QASystem.model.Project;
import com.example.QASystem.model.Step;
import com.example.QASystem.model.TestCase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TestCaseDto {

    private Long id;
    private String testcaseName;
    private String testcaseDescription;
    private Long project;
    private List<StepDto> steps;

    public static TestCaseDto getDto(TestCase t){
        TestCaseDto dto = new TestCaseDto();
        dto.setId(t.getId());
        dto.setTestcaseName(t.getTestcaseName());
        dto.setTestcaseDescription(t.getTestcaseDescription());
        dto.setProject(t.getProject().getId());
        dto.setSteps(t.getSteps().stream().map(StepDto::getDto).collect(Collectors.toList()));
        return dto;



    }


}
