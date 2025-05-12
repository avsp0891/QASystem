package com.example.QASystem.model.dtos;

import com.example.QASystem.model.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class StepDto {

    private Long id;
    private Long number;
    private String step;
    private String resultStep;

    public static StepDto getDto(Step s) {
        return new StepDto(
                s.getId(),
                s.getNumber(),
                s.getStep(),
                s.getResultStep()
        );
    }

}
