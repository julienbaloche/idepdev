package com.idep.api.test;

import java.util.List;

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
		List<Message> messages =  this.messageRepository.findAll(); 
		return messages;
	}
	/*
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Message getMessageById(@RequestParam("id") long messageId) {
		Message message = this.messageRepository.findById(messageId).orElseThrow(E::new);
		
		return message; 
	}*/
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Message addMessage(@RequestBody Message newMessage) {
		return this.messageRepository.save(newMessage); 
	} 	
	
	
}
