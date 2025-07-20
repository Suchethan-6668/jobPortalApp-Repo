package com.pradata.app.controller;

import com.pradata.app.dto.ContactDto;
import com.pradata.app.model.Message;
import com.pradata.app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/contact/send")
    public ResponseEntity<?> send(@RequestBody ContactDto dto, Principal p) {
        messageService.sendMessage(p.getName(), dto.getRecipientUsername(), dto.getContent());
        return ResponseEntity.ok("Message sent.");
    }

    @GetMapping("/contact/inbox")
    public ResponseEntity<List<Message>> inbox(Principal p) {
        return ResponseEntity.ok(messageService.getReceivedMessages(p.getName()));
    }

    @GetMapping("/contact/sent")
    public ResponseEntity<List<Message>> sent(Principal p) {
        return ResponseEntity.ok(messageService.getSentMessages(p.getName()));
    }
}

