package com.example.QASystem.model.dtos;

import com.example.QASystem.model.CheckList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CheckListDto {


    private Long id;
    private String checkListName;
    private String checkListDescription;
    private Long project;
    private LocalDateTime dateOfCreated;
    private List<ItemDto> items;

    public static CheckListDto getDto(CheckList c) {
        CheckListDto dto = new CheckListDto();
        dto.setId(c.getId());
        dto.setCheckListName(c.getCheckListName());
        dto.setCheckListDescription(c.getCheckListDescription());
        dto.setProject(c.getProject().getId());
        dto.setDateOfCreated(c.getDateOfCreated());
        dto.setItems(c.getItems().stream().map(ItemDto::getDto).collect(Collectors.toList()));
        return dto;
    }

    public static List<CheckListDto> getListDto(List<CheckList> checkLists) {
        return checkLists.stream().map(CheckListDto::getDto).collect(Collectors.toList());
    }
}
