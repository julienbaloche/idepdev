package com.idep.api.message;

import java.util.List;

import com.idep.api.common.exceptionhandling.exception.ResourceNotFoundException;
import com.idep.api.user.User;
import com.idep.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    //GETs

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessage() {
        List<Message> messages = this.messageRepository.findAll();
        return messages;
    }


    @GetMapping("/sent-by")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessagesSentByUser(@RequestParam("senderId") long senderId) {
        User sender = this.userRepository.findById(senderId).orElseThrow(ResourceNotFoundException::new);
        List<Message> messages = this.messageRepository.findBySender(sender);
        return messages;
    }

    @GetMapping("/received-by")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessagesReceivedByUser(@RequestParam("receiverId") long receiverId) {
        User receiver = this.userRepository.findById(receiverId).orElseThrow(ResourceNotFoundException::new);
        List<Message> messages = this.messageRepository.findByReceiver(receiver);
        return messages;
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Message getMessageById(@PathVariable("id") long messageId) {
        Message message = this.messageRepository.findById(messageId).orElseThrow(ResourceNotFoundException::new);
        return message;
    }


    //POSTs

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Message addMessage(@RequestBody Message newMessage,
                              @RequestParam("senderId") long senderId,
                              @RequestParam("receiverId") long receiverId) {

        User sender = this.userRepository.findById(senderId).orElseThrow(ResourceNotFoundException::new);
        User receiver = this.userRepository.findById(receiverId).orElseThrow(ResourceNotFoundException::new);

        newMessage.setSender(sender);
        newMessage.setReceiver(receiver);

        return this.messageRepository.save(newMessage);
    }


    //DELETEs


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Message deleteMessageById(@PathVariable("id") long messageId) {
        Message messageToDelete = this.messageRepository.findById(messageId).orElseThrow(ResourceNotFoundException::new);
        this.messageRepository.delete(messageToDelete);
        return messageToDelete;
    }


}
