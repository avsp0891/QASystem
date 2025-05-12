package com.example.QASystem.model.dtos;


import com.example.QASystem.model.Project;
import com.example.QASystem.model.TestCase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProjectDto {

    private Long id;
    private String projectName;
    private String projectDescription;
    private String projectStatus;
    private List<Long> testCases;
    private LocalDateTime dateOfCreated;

    public static ProjectDto getDto(Project p) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(p.getId());
        projectDto.setProjectName(p.getProjectName());
        projectDto.setProjectDescription(p.getProjectDescription());
        projectDto.setProjectStatus(p.getProjectStatus().toString());
        projectDto.setDateOfCreated(p.getDateOfCreated());
        if (p.getTestCases() != null) {
            List<Long> testCases = p.getTestCases().stream().map(TestCase::getId).toList();
            projectDto.setTestCases(testCases);
        } else {
            projectDto.setTestCases(new ArrayList<>());
        }
        return projectDto;
    }


}
