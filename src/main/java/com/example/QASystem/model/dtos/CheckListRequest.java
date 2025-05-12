package com.example.QASystem.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckListRequest {

    private String checkListName;
    private String checkListDescription;
    private Long project;
    private List<ItemRequest> items;

}
