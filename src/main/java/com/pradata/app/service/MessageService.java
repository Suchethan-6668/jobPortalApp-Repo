package com.pradata.app.service;

import com.pradata.app.model.Message;
import com.pradata.app.model.User;
import com.pradata.app.repo.MessageRepo;
import com.pradata.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired private UserRepo userRepo;

    public Message sendMessage(String senderUsername, String recipientUsername, String content) {
        User sender = userRepo.findByUsername(senderUsername);
        User recipient = userRepo.findByUsername(recipientUsername);
        // Add role-based validation here if needed
        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);
        message.setSentAt(LocalDateTime.now());
        return messageRepo.save(message);
    }

    public List<Message> getReceivedMessages(String username) {
        User user = userRepo.findByUsername(username);
        return messageRepo.findByRecipient(user);
    }

    public List<Message> getSentMessages(String username) {
        User user = userRepo.findByUsername(username);
        return messageRepo.findBySender(user);
    }
}

