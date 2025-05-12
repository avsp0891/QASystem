package com.example.QASystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "check_list")
public class CheckList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "checklist_name")
    private String checkListName;

    @Column(name = "checklist_description")
    private String checkListDescription;
    private LocalDateTime dateOfCreated;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany(mappedBy = "checkList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Item> items;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

}
