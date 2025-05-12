package com.example.QASystem.model.dtos;

import com.example.QASystem.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private Long number;
    private Long testCaseId;
    private String item;

    public static ItemDto getDto(Item i) {
        return new ItemDto(
                i.getId(),
                i.getNumber(),
                i.getTestCaseId(),
                i.getItem()
        );
    }

}
