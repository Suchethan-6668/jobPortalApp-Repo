package com.pradata.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private String username;
    private String email;
    private String password;
    private Boolean wantsEmployerRole;
    // If employer
    private String companyName;
    private String companyWebsite;
    // If employee
    private String resumeLink;
    private String skills;

}

