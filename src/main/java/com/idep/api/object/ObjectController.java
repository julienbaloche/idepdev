package com.idep.api.object;

import com.idep.api.common.exceptionhandling.exception.ResourceNotFoundException;
import com.idep.api.message.Message;
import com.idep.api.user.User;
import com.idep.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objects")
public class ObjectController {

    private final ObjectRepository objectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ObjectController(ObjectRepository objectRepository, UserRepository userRepository) {
        this.objectRepository = objectRepository;
        this.userRepository = userRepository;
    }

    //GETs

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getAllObjects() {
        List<Object> objects = this.objectRepository.findAll();
        return objects;
    }

    @GetMapping("/owned-by")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getAllObjectsOwnedByUser(@RequestParam("ownerId") long ownerId) {
        User owner = this.userRepository.findById(ownerId).orElseThrow(ResourceNotFoundException::new);
        List<Object> objects = this.objectRepository.findByOwner(owner);
        return objects;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getObjectById(@PathVariable("id") long objectId) {
        Object object = this.objectRepository.findById(objectId).orElseThrow(ResourceNotFoundException::new);
        return object;
    }

    //POSTs

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addObject(@RequestBody Object newObject,
                              @RequestParam("ownerId") long ownerId) {
        User owner = this.userRepository.findById(ownerId).orElseThrow(ResourceNotFoundException::new);
        newObject.setOwner(owner);
        return this.objectRepository.save(newObject);
    }
    //DELETEs


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteObjectById(@PathVariable("id") long objectId) {
        Object objectToDelete = this.objectRepository.findById(objectId).orElseThrow(ResourceNotFoundException::new);
        this.objectRepository.delete(objectToDelete);
        return objectToDelete;
    }


}
