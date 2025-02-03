package com.tecnotree.automatiom.SS0_aaccess_token;

import java.time.Instant;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class ResponseFromSSO {
	
	//static Specification spe = new Specification();

	static String access_token = null;
	static Instant expire;

    //static CreateApplicationProperities appProperties = new CreateApplicationProperities();
    
	
	public static String gettoken() {
		Response restoken = TokenCreation();

		if (access_token == null || Instant.now().isAfter(expire)) {
			try {
				int expiretime = restoken.jsonPath().getInt("expires_in");
				expire = Instant.now().plusSeconds(expiretime - 300);

			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} else {
            System.out.println("Token is still valid");
		}
		return restoken.jsonPath().getString("access_token").toString();
	}

	public static Response TokenCreation() {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("client_id", Readfromapplicationprpoerities.application("application.properties", "client_id"));
		parameters.put("client_secret", Readfromapplicationprpoerities.application("application.properties", "client_secret"));
		parameters.put("grant_type", Readfromapplicationprpoerities.application("application.properties", "grant_type"));
		parameters.put("scope", Readfromapplicationprpoerities.application("application.properties", "scope"));
		parameters.put("username", Readfromapplicationprpoerities.application("application.properties", "ssousername"));
		parameters.put("password", Readfromapplicationprpoerities.application("application.properties", "ssopassword"));

		Response res = RestAssured
				.given()
					.baseUri("https://sso4.tecnotree.com")
					.params(parameters)
					.contentType(ContentType.URLENC)
					.log().all()
				.when()
					.post("/oauth2/token")
				.then()
				.log().all()
					.statusCode(200)
					.extract()
					.response();

		if (res.getStatusCode() != 200) {
			throw new RuntimeException("404");
		}
		return res;
		// return res.jsonPath().getString("access_token").toString();
	}

	public static void main(String args[]) {

		System.out.println(Readfromapplicationprpoerities.application("Application.properties", "client_id"));
		String access_token = ResponseFromSSO.gettoken();
		System.out.println("access_token--"+access_token);
		
	}

}
