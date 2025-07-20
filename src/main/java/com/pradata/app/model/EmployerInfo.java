package com.pradata.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employer_info")
public class EmployerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @OneToOne @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(nullable=false)
    private String companyName;

    private String companyWebsite;
    // getters/setters
}
