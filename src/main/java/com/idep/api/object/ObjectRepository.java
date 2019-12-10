package com.idep.api.object;

import com.idep.api.message.Message;
import com.idep.api.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObjectRepository extends CrudRepository<Object, Long> {
    List<Object> findAll();
    List<Object> findByOwner(User owner);
}
