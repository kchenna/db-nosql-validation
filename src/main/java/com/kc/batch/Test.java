package com.kc.batch;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	
	public static void main(String[] args) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		String s1 = "{\n" + 
				"    \"employee\":\n" + 
				"    {\n" + 
				"        \"id\": \"1212\",\n" + 
				"        \"age\": 34,\n" + 
				"        \"fullName\": \"John Miles\",\n" + 
				"        \"contact\":\n" + 
				"        {\n" + 
				"            \"email\": \"john@xyz.com\",\n" + 
				"            \"phone\": \"9999999999\"\n" + 
				"        }\n" + 
				"    }\n" + 
				"}";
		String s2 = "{ \n" + 
				"    \"employee\":\n" + 
				"    {\n" + 
				"        \"id\": \"1212\",\n" + 
				"        \"fullName\":\"John Miles\",\n" + 
				"        \"age\": 34,\n" + 
				"        \"contact\":\n" + 
				"        {\n" + 
				"            \"email\": \"john@xyz.com\",\n" + 
				"            \"phone\": \"9999999999\"\n" + 
				"        }\n" + 
				"    }\n" + 
				"}";
		
		System.out.println(mapper.readTree(s1).equals(mapper.readTree(s2)));
		
		
	}
}
