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
@Entity(name = "test_cases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "testcase_name")
    private String testcaseName;

    @Column(name = "testcase_description")
    private String testcaseDescription;

    private LocalDateTime dateOfCreated;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Step> steps;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

//    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Item> items;

    // TODO: 22.04.2025  постусловия, вложения, комментарии, версия, дата создания, приоритет, статус

}
