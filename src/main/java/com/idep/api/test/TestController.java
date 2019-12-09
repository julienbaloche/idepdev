package com.idep.api.test;
import java.util.*;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.sql.*;


@RestController
@RequestMapping("/test")
public class TestController {
	
	

	Object testobj =	new Object (1, "Le Sexe","Julien Baloche Roussex","ceci est un livre super"
    		,"book", 5, 69, false);
	
	User testuser = new User (1, "Lucas", "EHMERSEX", "lucas@mrsex.fr", 420, true);
	
	
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/objinfo")
    public String getString3() {
        return Object.objectToJson(testobj);
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/userinfo")
    public String getString2() {
        return User.userToJson(testuser);

    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/userlistinfo")
    public List getStringUserList() {
    	return UserDB.userList;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/objectlistinfo")
    public List getStringObjectList() {
    	return ObjectDB.objectList;
    }
    
    
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/adduserbdd/{id}/{forename}/{surname}/{mail}/{balance}/{isAdmin}")
    
    public static void main (@PathVariable("id") int id, @PathVariable("forename") String forename,
    		@PathVariable("surname") String surname, @PathVariable("mail") String mail, @PathVariable("balance") float balance,
    		@PathVariable("isAdmin") boolean isAdmin) { 
        try { 
            String url = "jdbc:mysql://root:root@localhost:3306/idep"; 
            Connection conn = DriverManager.getConnection(url,"root","root"); 
            Statement st = conn.createStatement(); 
            String instru = "VALUES ("+id+",'"+forename+"','"+surname+"','"+mail+"',"+balance+","+isAdmin+")";
            System.out.println(instru);
            st.executeUpdate("INSERT INTO User " + instru); 
            
            
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 
    
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value ="/userlistbdd")
    @ResponseBody
    public List getString18() {
    	
    UserDB.userListBdd.clear();	
    	try { 
             
    		 String url = "jdbc:mysql://root:root@localhost:3306/idep"; 
             Connection conn = DriverManager.getConnection(url,"root","root"); 
             Statement st = conn.createStatement(); 
             ResultSet resultat = st.executeQuery("SELECT * FROM USER"); 
             while(resultat.next()){
                 int id  = resultat.getInt(1);
                 String forename = resultat.getString(2);
                 String surname= resultat.getString(3);
                 String mail = resultat.getString(4);
                 float balance = resultat.getFloat(5);
                 boolean isAdmin = resultat.getBoolean(6);
                 
                 User adduser = new User (id, forename, surname, mail, balance, isAdmin);
                 UserDB.userListBdd.add(adduser);
             }
         return UserDB.userListBdd;    
             
         } catch (Exception e) { 
             System.err.println("Got an exception! "); 
             System.err.println(e.getMessage()); 
             
         }
		return null; 
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value ="/userinfo/{id}")
    @ResponseBody
    public String getString4(@PathVariable("id") int id) {
    	User neededuser = UserDB.getUserFromID(id);
        return User.userToJson(neededuser);
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value ="/objinfo/{id}")
    @ResponseBody
    public String getString5(@PathVariable("id") int id) {
    	Object neededobject = ObjectDB.getObjectFromID(id);
        return Object.objectToJson(neededobject);
    }
    
    
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value ="/removeobject/{id}")
    @ResponseBody
    public String getString6(@PathVariable("id") int id) {
    	ObjectDB.removeObjectFromDB(id);
        return ("L'objet d'id: "+ id + " a été supprimé.");
    }
    
  
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value ="/addobject/{id}/{title}/{author}/{description}/{categoryID}"
    		+ "/{price}/{ownerID}/{isBorrowed}")
    @ResponseBody
    public String getString7(@PathVariable("id") int id, @PathVariable("title") String title,
    		 @PathVariable("author") String author, @PathVariable("description") String description,
    		 @PathVariable("categoryID") String categoryID, @PathVariable("price") float price,
    		 @PathVariable("ownerID") int  ownerID, @PathVariable("isBorrowed") Boolean isBorrowed) {
    Object newobj =	new Object (id, title, author, description,
        		categoryID , price, ownerID, isBorrowed);	
    String newobjinfo = Object.objectToJson(newobj);
    
    
    int idcondi = newobj.getId();
    
    	for (Object potentialmatch : ObjectDB.objectList) {
    		if (potentialmatch.getId() == idcondi) {
    			return("objectID already in DB, no object created.");
    		}
    	}
			
	ObjectDB.objectList.add(newobj);
	return("L'objet suivant a été créé : \n"+ newobjinfo);
    
    
    

    }
    
}
