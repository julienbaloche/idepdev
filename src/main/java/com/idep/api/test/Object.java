package com.idep.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Object {

	private Integer id;
	
	private String title;
	
	private String author; 
	
	private String description;
	
	private String categoryID;
	
	private float price;
	
	private int ownerID; 
	
	private boolean isBorrowed;
	
	public static int index = 0;
	
	
	

	public Object(int id,String title, String author, String description, String categoryID,
			float price, int ownerID, boolean isBorrowed) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.categoryID = categoryID;
		this.price = price;
		this.ownerID = ownerID;
		this.isBorrowed = isBorrowed;
	}

	 public static String objectToJson (Object object) {
	    	
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	try {
	    	  String json = mapper.writeValueAsString(object);
	    	 return json;
	    	  //System.out.println(json);
	    	} catch (JsonProcessingException e) {
	    	   e.printStackTrace();
	    	}
			return null;
	    }
	
	
	
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	
	public boolean getIsBorrowed() {
		return isBorrowed;
	}

	public void setIsBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
	
	
}
	

