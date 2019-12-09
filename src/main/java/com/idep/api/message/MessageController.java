package com.idep.api.message;

import java.util.List;

import com.idep.api.common.exceptionhandling.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessage() {
        List<Message> messages = this.messageRepository.findAll();
        return messages;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Message getMessageById(@PathVariable("id") long messageId) {
        Message message = this.messageRepository.findById(messageId).orElseThrow(ResourceNotFoundException::new);
        return message;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Message addMessage(@RequestBody Message newMessage) {
        return this.messageRepository.save(newMessage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Message deleteMessageById(@PathVariable("id") long messageId) {
        Message messageToDelete = this.messageRepository.findById(messageId).orElseThrow(ResourceNotFoundException::new);
        this.messageRepository.delete(messageToDelete);
        return messageToDelete;
    }


}
