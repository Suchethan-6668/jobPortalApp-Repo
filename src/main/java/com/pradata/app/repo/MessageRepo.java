package com.pradata.app.repo;

import com.pradata.app.model.Message;
import com.pradata.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByRecipient(User recipient);
    List<Message> findBySender(User sender);
}
