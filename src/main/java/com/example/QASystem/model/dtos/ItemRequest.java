package com.example.QASystem.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemRequest {
    private Long number;
    private String item;
    private Long testCaseId;
}
