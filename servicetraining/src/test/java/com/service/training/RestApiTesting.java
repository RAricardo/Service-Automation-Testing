package com.service.training;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class RestApiTesting {
	private String url = "http://localhost:3000/api";
	private String auxId;
	
	@Test
	/*
	 * Initial test to test connection to endpoint, should assert status code 200 if everything goes ok.
	 */
	public void testResponseCode() {
		int code = get(url).getStatusCode();
		Assert.assertEquals(code, 200, "Response Code Test Passed Successfully");
	}
	
	@Test (dependsOnMethods={"testResponseCode"})
	/*
	 * Tests get request by querying an existing user in the api database, and comparing the results in the response.
	 */
	
	public void testGetRequest() {
		Contact testContact = new Contact("5f32bd6aae1b1432fcfd8018",
				"ricardo", "ricardoazo20@gmail.com", "male", "3234996908");
		JsonPath body = get(url + "/contacts/" + testContact.getId()).getBody().jsonPath();
		Assert.assertEquals(body.get("data.name"), testContact.getName());
		Assert.assertEquals(body.get("data.email"), testContact.getEmail());
		Assert.assertEquals(body.get("data.gender"), testContact.getGender());
		Assert.assertEquals(body.get("data.phone"), testContact.getPhone(), "Get Request Test Passed Successfully");
	}
	
	@Test (dependsOnMethods={"testGetRequest"})
	/*
	 * Tests post request by creating a new contact and then querying the newly created contact with a get request,
	 * in order to compare input fields with get request response.
	 */
	public void testPostRequest() {
		JSONObject json = new JSONObject();
		json.put("name", "maria");
		json.put("email", "maria@gmail.com");
		json.put("gender", "female");
		json.put("phone", "123456");
		//A post request to create new user maria is created
		JsonPath postbody = RestAssured.given().contentType(ContentType.JSON).
				body(json.toString()).post(url+"/contacts").thenReturn().body().jsonPath();
		//A get request to query newly created user and assert fields with previous post request parameters.
		auxId = postbody.get("data._id");
		JsonPath body = get(url + "/contacts/" + auxId).getBody().jsonPath();
		Assert.assertEquals(body.get("data.name"), json.get("name"));
		Assert.assertEquals(body.get("data.email"), json.get("email"));
		Assert.assertEquals(body.get("data.gender"), json.get("gender"));
		Assert.assertEquals(body.get("data.phone"), json.get("phone"), "Post Request Test Passed Successfully");
	}
	
	@Test (dependsOnMethods={"testPostRequest"})
	/*
	 * Tests put request to update an existing field in the api database. The phone number of the previously created
	 * user in the post request will be updated, this will be followed by a get request to assert the updated phone number.
	 */
	public void testPutRequest() {
		JSONObject json = new JSONObject();
		json.put("name", "maria");
		json.put("email", "maria@gmail.com");
		json.put("gender", "female");
		json.put("phone", "654321");
		RestAssured.given().contentType(ContentType.JSON).body(json.toString())
		.put(url + "/contacts/" + auxId);
		JsonPath body = get(url + "/contacts/" + auxId).getBody().jsonPath();
		Assert.assertEquals(body.get("data.name"), json.get("name"));
		Assert.assertEquals(body.get("data.email"), json.get("email"));
		Assert.assertEquals(body.get("data.gender"), json.get("gender"));
		Assert.assertEquals(body.get("data.phone"), json.get("phone"), "Put Request Test Passed Successfully");
	}
	
	@Test (dependsOnMethods={"testPutRequest"})
	/*
	 * Tests delete request, deletes previously created and updated contact, then tries to get this contact using
	 * the get method, the test will pass if it successfully asserts a null.
	 */
	public void deleteRequest() {
		delete(url + "/contacts/" + auxId);
		JsonPath body = get(url + "/contacts/" + auxId).getBody().jsonPath();
		System.out.println(body.get("data"));
		Assert.assertEquals(body.get("data"), null, "Delete Request Test Passed Successfully");
	}
}
