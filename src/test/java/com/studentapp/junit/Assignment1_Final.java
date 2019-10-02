package com.studentapp.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
public class Assignment1_Final {
	
	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "http://restcountries.eu/rest/v1";
		
	}
	
	@Title ("This Test verifies If expected Txt is displayed in the JSON response received")
	@Test
	public void test001() {
		
		String expectedTxt = "Republic of India";
		
		ArrayList value = RestAssured.given()
		.when()
		.get("/name/INDIA")
		.then()
		.statusCode(200)
		.log()
		.all()
		.extract()
		.path("altSpellings");
		
		System.out.println(value.toString().contains(expectedTxt));
		assertTrue(value.toString().contains(expectedTxt));
		
	}
	
	@Title ("This Test verifies If 404 error is displayed If wrong URI is given")
	@Test
	public void test002() {
		
		String msg = RestAssured.given()
		.when()
		.get("/name/xyz")
		.then()
		.statusCode(404)
		.log()
		.all()
		.extract()
		.path("message");
		
		assertEquals(msg, "Not Found");
		
	}
}

