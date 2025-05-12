package com.example.QASystem.model.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestCaseRequest {

    private String testcaseName;
    private String testcaseDescription;
    private Long project;
    private List<StepRequest> steps;


}
