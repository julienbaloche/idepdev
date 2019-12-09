package com.idep.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.idep.api.test.ObjectDB;
import com.idep.api.test.UserDB;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
//		UserDB.getSampleUserList();
//		ObjectDB.getSampleObjectList();
		SpringApplication.run(ApiApplication.class, args);
	}

}

