package com.idep.api.test;
import java.util.*;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/test")
public class TestController {

	Object testobj =	new Object (1, "Le Sexe","Julien Baloche Roussex","ceci est un livre super"
    		,"book", 5, 69, false);
	
	User testuser = new User (1, "Lucas", "EHMERSEX", "lucas@mrsex.fr", 420, true);
	
	
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/objinfo")
    public String getString() {
        return Object.objectToJson(testobj);
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/userinfo")
    public String getString2() {
        return User.userToJson(testuser);
    }
    
    
   
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/1")
    public String getTitle() {
		
    
    Object testone =	new Object (1, "Le Sexe","Julien Baloche Roussex","ceci est un livre super"
    		,"book", 5, 69, false);
    
    
    return testone.getTitle();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/2")
    public void declareObject() {
    
    	
    System.out.println("Bonjour!\nBienvenue sur IDEP!\nEntrez les infos du livre que vous souhaitez échanger");
	Scanner sc = new Scanner(System.in);
	System.out.println("Titre?");
	String titre = sc.next();
	System.out.println("Vous avez entré le titre suivant:"+ titre);
    	

	
    }
    
  
    
}


