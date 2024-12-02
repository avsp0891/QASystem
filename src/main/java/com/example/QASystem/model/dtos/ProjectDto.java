package com.example.QASystem.model.dtos;


import com.example.QASystem.model.Project;
import com.example.QASystem.model.TestCase;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class ProjectDto {

    private Long id;

    private String projectName;

    private String projectDescription;

    private List<Long> testCases;

    public static ProjectDto getDto(Project p){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(p.getId());
        projectDto.setProjectName(p.getProjectName());
        projectDto.setProjectDescription(p.getProjectDescription());
        if (p.getTestCases() != null){
            List<Long> testCases = p.getTestCases().stream().map(TestCase::getId).toList();
            projectDto.setTestCases(testCases);
        } else {
            projectDto.setTestCases(new ArrayList<>());
        }
        return projectDto;
    }


}
