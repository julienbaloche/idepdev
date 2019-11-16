package com.idep.api.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public String getString() {
        return "Hello";
    }
}


