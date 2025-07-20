package com.pradata.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_info")
public class EmployeeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @OneToOne @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String resumeLink;

    @Column(columnDefinition="TEXT")
    private String skills;
    // getters/setters
}
