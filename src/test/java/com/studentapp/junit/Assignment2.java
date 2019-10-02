package com.studentapp.junit;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class Assignment2 {
	
	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "http://restcountries.eu/rest/v1";
		
	}
	
	@Title ("This Test verifies If expected Txt is displayed in the JSON response received")
	@Test
	public void test001() {
		
		String expectedTxt = "Oslo";
		
		List value = RestAssured.given()
		.when()
		.get("/name/norway")
		.then()
		.statusCode(200)
		.log()
		.all()
		.extract()
		.path("capital");
		
		System.out.println(value.toString());
		
		assertTrue(value.toString().contains(expectedTxt));
		
	}
	
}

