package com.pradata.app.repo;

import com.pradata.app.model.EmployerInfo;
import com.pradata.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerInfoRepo extends JpaRepository<EmployerInfo, Integer> {
    EmployerInfo findByUser(User user);
}
