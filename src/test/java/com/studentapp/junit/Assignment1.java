package com.studentapp.junit;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

//@RunWith(SerenityRunner.class)
public class Assignment1 {
	
	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "http://restcountries.eu/rest/v1";
		
	}
	
	@Test
	public void test001() {
		
		String expectedTxt = "Republic of India";
		
		ArrayList value = RestAssured.given()
		.when()
		.get("/name/INDIA")
		.then()
		.statusCode(200)
		.extract()
		.path("altSpellings");
		
		System.out.println(value.toString().contains(expectedTxt));
		
		System.out.println("The value is: " + value);
		
		for(int i=0;i<value.size();i++) {
			
			
			ArrayList<String> temp = new ArrayList<String>();
			temp = (ArrayList<String>) value.get(i);
			System.out.println(temp);
			
			int expected_index = temp.indexOf(expectedTxt);
			if (expected_index != -1)
				System.out.println("found in " + i);
			else
				System.out.println("Not found in " + i);
			
				
			
			
//			for (int j=0;j<temp.size();j++) {
//				
//				if(temp.get(j).toString().equals("Republic of India"))
//					System.out.println("True");
//				
//			}
			
		}
		
	}
}

