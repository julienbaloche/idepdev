package com.idep.api.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ObjectDB {

	public static List<Object> objectList = new ArrayList<Object>();
	
	static Object testobj1 =	new Object (1, "Le Sexe","Julien Baloche Roussex",
			"ceci est un livre super" ,"book", 5, 3, false);
	static Object testobj2 =	new Object (2, "Le Thé","Julien  Roussex",
			"ceci est un livre bof" ,"book", 5, 3, false);
	static Object testobj3 =	new Object (14, "Le café","Julien Baloche Roussex",
			"ceci est un livre nul" ,"book", 5, 3, true);
	
	
	
	public static void getSampleObjectList() {
		
		
		objectList.add(testobj1);
		objectList.add(testobj2);
		objectList.add(testobj3);
		
	}
	
	public static Object getObjectFromID(int id) {
		for (Object potentialmatch : objectList) {
			if (potentialmatch.getId() == id) {
				return potentialmatch;
			}
			
		}
		return null;
		
	}
	
	public static void  addObjectToDB (Object newobject) {
		objectList.add(newobject);
		
	}
	
	public static void  removeObjectFromDB (int removeid) {
		for (Object potentialmatch : objectList) {
			if (potentialmatch.getId() == removeid) {
				objectList.remove(potentialmatch);
			}
		}

	}		
}
