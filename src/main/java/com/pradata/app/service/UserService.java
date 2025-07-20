package com.pradata.app.service;


import com.pradata.app.dto.RegistrationDto;
import com.pradata.app.model.EmployeeInfo;
import com.pradata.app.model.EmployerInfo;
import com.pradata.app.model.User;
import com.pradata.app.repo.EmployeeInfoRepo;
import com.pradata.app.repo.EmployerInfoRepo;
import com.pradata.app.repo.RoleRepo;
import com.pradata.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserRepo repo;
    @Autowired private RoleRepo roleRepo;
    @Autowired private EmployeeInfoRepo employeeInfoRepo;
    @Autowired private EmployerInfoRepo employerInfoRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUserWithRole(RegistrationDto dto) {
        if (dto.getWantsEmployerRole() == null) throw new IllegalArgumentException("Choose employer/employee:");
        String pickedRole = dto.getWantsEmployerRole() ? "EMPLOYER" : "EMPLOYEE";
        if (pickedRole.equals("ADMIN")) throw new IllegalArgumentException("Admin self-registration forbidden");
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(roleRepo.findByRoleName(pickedRole));
        User saved = repo.save(user);

        if ("EMPLOYER".equals(pickedRole)) {
            EmployerInfo info = new EmployerInfo();
            info.setUser(saved);
            info.setCompanyName(dto.getCompanyName());
            info.setCompanyWebsite(dto.getCompanyWebsite());
            employerInfoRepo.save(info);
        } else {
            EmployeeInfo info = new EmployeeInfo();
            info.setUser(saved);
            info.setResumeLink(dto.getResumeLink());
            info.setSkills(dto.getSkills());
            employeeInfoRepo.save(info);
        }
        return saved;
    }

    public String getRole(String username) {
        User user = repo.findByUsername(username);
        return user.getRole().getRoleName();
    }
}
