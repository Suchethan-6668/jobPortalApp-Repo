package com.pradata.app.repo;

import com.pradata.app.model.EmployeeInfo;
import com.pradata.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfo, Integer> {
    EmployeeInfo findByUser(User user);
}
