package com.studentapp.junit;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class Assignment3 {
	
	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "http://restapi.demoqa.com/customer/register";
		
	}
	
	public static String getRandomValue() {

		Random random = new Random();
		int rnum = random.nextInt(100000);
		return Integer.toString(rnum);

	}
	
	@Title ("This Test verifies If user is succesfully added and If same user is added another response is displayed")
	@Test
	public void test001() {
		
		String extratxt = getRandomValue();
			
		String expectedTxt1 = "OPERATION_SUCCESS";
		
		String FirstName = "Avi"+extratxt;
		String LastName = "Seelam"+extratxt;
		String UserName = "Avi4010"+extratxt;
		String Password = "Avi@123"+extratxt;
		String Email = "Avi@gmial.com"+extratxt;
		
		Assignment3Student stu = new Assignment3Student();
		stu.setFirstName(FirstName);
		stu.setLastName(LastName);
		stu.setUserName(UserName);
		stu.setPassword(Password);
		stu.setEmail(Email);
		
		String value = RestAssured.given()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.when()
			.body(stu)
			.post()
			.then()
			.log()
			.all()
			.statusCode(201)
			.extract()
			.path("SuccessCode");
			
		System.out.println("The first value is "+value);
		
		assertEquals(value, expectedTxt1);
		
		String expectedTxt2 = "FAULT_USER_ALREADY_EXISTS";
		
		String value2 = RestAssured.given()
				.contentType(ContentType.JSON)
				.log()
				.all()
				.when()
				.body(stu)
				.post()
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.path("fault");
		
		System.out.println("The second value is "+value2);
		assertEquals(value2, expectedTxt2);
		
	}
	
}

