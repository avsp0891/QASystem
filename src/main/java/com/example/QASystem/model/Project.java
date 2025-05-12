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
@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_status")
    private ProjectStatus projectStatus;

    private LocalDateTime dateOfCreated;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCase> testCases;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckList> checkLists;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }


}
