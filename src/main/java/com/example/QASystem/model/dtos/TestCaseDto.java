package com.example.QASystem.model.dtos;


import com.example.QASystem.model.TestCase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime dateOfCreated;

    public static TestCaseDto getDto(TestCase t) {
        TestCaseDto dto = new TestCaseDto();
        dto.setId(t.getId());
        dto.setTestcaseName(t.getTestcaseName());
        dto.setTestcaseDescription(t.getTestcaseDescription());
        dto.setProject(t.getProject().getId());
        dto.setDateOfCreated(t.getDateOfCreated());
        dto.setSteps(t.getSteps().stream().map(StepDto::getDto).collect(Collectors.toList()));
        return dto;
    }

    public static List<TestCaseDto> getListDto(List<TestCase> testCases) {
        return testCases.stream().map(TestCaseDto::getDto).collect(Collectors.toList());
    }


}
