package com.idep.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {

	private int id;
	
	private String forename;
	
	private String surname;
	
	private String mail;
	
	private float balance;
	
	private boolean isAdmin;
	
	
	
	public User(int id, String forename, String surname, String mail, float balance, boolean isAdmin) {
		super();
		this.id = id;
		this.forename = forename;
		this.surname = surname;
		this.mail = mail;
		this.balance = balance;
		this.isAdmin = isAdmin;
	}


	public static String userToJson (User user) {
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    	  String json = mapper.writeValueAsString(user);
    	 return json;
    	  //System.out.println(json);
    	} catch (JsonProcessingException e) {
    	   e.printStackTrace();
    	}
		return null;
    }
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getForename() {
		return forename;
	}



	public void setForename(String forename) {
		this.forename = forename;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public float getBalance() {
		return balance;
	}



	public void setBalance(float balance) {
		this.balance = balance;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	
}
