package com.example.QASystem.model.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ProjectRequest {

    private String projectName;
    private String projectDescription;
    private String projectStatus;

}
