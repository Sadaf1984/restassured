package com.testcases.APITesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


import io.restassured.response.Response;

public class APITest {
	
	@Test
	public void Hotel_API() {
		
		String URL ="https://www.almosafer.com/api/enigma/search/async";
		String ContentType ="application/json";
		String Body ="{\"checkIn\":\"2022-12-08\",\"checkOut\":\"2022-12-09\",\"roomsInfo\":[{\"adultsCount\":2,\"kidsAges\":[]}],\"placeId\":\"ChIJRcbZaklDXz4RYlEphFBu5r0\"}";
		
		Response res = RestAssured
				.given()
				.header("token","skdjfh73273$7268u2j89s")
				.contentType(ContentType)
				.body(Body)
				.when()
				.post(URL)
				.then()
				.extract()
				.response();
		System.out.println(res.asPrettyString());
		Assert.assertEquals(res.statusCode(),200, "Response code match");
		

	}
	@Test(enabled = true)
	public void Flights_Code_Verification() {
		
		String flight_list_endpoint = "https://www.almosafer.com/api/v3/flights/flight/search";
		
		String Start_Date ="2022-11-25";
		String End_Date ="2022-12-20";
		
		String queryString = "RUH-JED/"+Start_Date+"/"+End_Date+"/Economy/2Adult";
		
	//----------------------------------------------------------------------//	
		//Get Origin Code from flight search API
		 String Origin_Code = given().queryParam("query", queryString).when().get(flight_list_endpoint)
				.then().extract().path("request.leg[0].originId");
		 System.out.println(Origin_Code);
		 
		//Get Destination Code from flight search API
		 String DestinationId = given().queryParam("query", queryString).when().get(flight_list_endpoint)
					.then().extract().path("request.leg[0].destinationId");
		 System.out.println(DestinationId);
	//----------------------------------------------------------------------//	 
		 
		 //Verify the Destination ID should be as per expectation
		 try {
			 if(DestinationId.equalsIgnoreCase("JED")) {
					System.out.println("Destination Code in matched");
					}else {
						System.out.println("Destination Code in not matched");
					}
			
		} catch (Exception e) {
			System.out.println("Unable to find Destination parameter");
		}
		//Verify the Origin ID should be as per expectation
		 try {
			 if(Origin_Code.equalsIgnoreCase("RUH")) {
				 System.out.println("Origin Code is matched");
			 }else {
				 System.out.println("Origin Code is not matched");
			 }
			 
			
		} catch (Exception e) {
			
			System.out.println("Unable to find Origin parameter");
		}
		
			 
		 
		
		
	}
	
}

