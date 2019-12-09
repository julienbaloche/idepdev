package com.idep.api.message;

import java.util.List;

import com.idep.api.user.User;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long>{
	List<Message> findAll();
	List<Message> findBySender(User sender);
	List<Message> findByReceiver(User receiver);
}
