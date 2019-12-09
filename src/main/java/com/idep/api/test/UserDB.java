package com.idep.api.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class UserDB {
	
	
	public static List<User> userList = new ArrayList<User>();
	
	
	public static List<User> userListBdd = new ArrayList<User>();
	
	
	static User testuser = new User (1, "Lucas", "EHMERSEX", "lucas@mrsex.fr", 420, true);
	static User testuser2 = new User (2, "Matthieu", "Jacquand", "mat@mat.fr", 500, true);
	static User testuser3 = new User (3, "Julien", "BR", "juju@br.fr", 200, false);
	static User testuser4 = new User (56, "Wesh", "Alors", "jul.fr", 500000, false);
	
	
	
	public static void getSampleUserList() {
	
		userList.add(testuser);
		userList.add(testuser2);
		userList.add(testuser3);
		userList.add(testuser4);
		
	}
	
	public static User getUserFromID(int id) {
		for (User potentialmatch : userList) {
			if (potentialmatch.getId() == id) {
				return potentialmatch;
			}
			
		}
		return null;
		
	}
	
	
	
	
}
