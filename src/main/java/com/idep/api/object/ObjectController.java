package com.idep.api.object;

import com.idep.api.common.exceptionhandling.exception.ResourceNotFoundException;
import com.idep.api.message.Message;
import com.idep.api.user.User;
import com.idep.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/objects")
public class ObjectController {

    private final ObjectRepository objectRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Autowired
    public ObjectController(ObjectRepository objectRepository, UserRepository userRepository, ImageService imageService) {
        this.objectRepository = objectRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
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

    @PatchMapping ("/modif/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object editObjectById(@PathVariable("id") long objectId,
                                 @RequestBody Object requestobj) {
        Object object = this.objectRepository.findById(objectId).orElseThrow(ResourceNotFoundException::new);
        object.setTitle(requestobj.getTitle());
        object.setAuthor(requestobj.getAuthor());
        object.setDescription(requestobj.getDescription());
        object.setCategory(requestobj.getCategory());
        object.setPrice(requestobj.getPrice());
        return object;
    }


    //IMAGE UPLOAD
    @PostMapping("/{id}/uploadFile")
    public Object uploadFile(@PathVariable("id") long objectId, @RequestPart(value = "file") MultipartFile file) {
        Object object = this.objectRepository.findById(objectId).orElseThrow(ResourceNotFoundException::new);

        String fileUrl = this.imageService.uploadFile(file);

        object.setImageUrl(fileUrl);
        return this.objectRepository.save(object);
    }

    //IMAGE DELETE
    @DeleteMapping("/{id}/deleteFile")
    public Object deleteFile(@PathVariable("id") long objectId) throws Exception {
        Object object = this.objectRepository.findById(objectId).orElseThrow(ResourceNotFoundException::new);
        boolean isDeleted = this.imageService.deleteFileFromS3Bucket(object.getImageUrl());

        if (isDeleted) {

            object.setImageUrl(null);
            return objectRepository.save(object);
        }
        throw new Exception("Error while deleting");
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
