package com.tecnotree.automatiom.Methods;

import com.tecnotree.automatiom.Specifications.Specifiactions;
import com.tecnotree.automatiom.routers.ValidatorOperation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.empty;

public class Method {

	Response response;

	public Method Post(String requestFormat, String baseUrl, String middleUrl, String endpoints) {
		response = RestAssured
				.given(Specifiactions.Requestspeci(baseUrl, middleUrl)).body(requestFormat)
				.when().post(endpoints)
				.then()
				.spec(Specifiactions.responsespeci())
				.extract()
				.response();
		return this;
	}

	public Method Post(String requestFormat, String baseUrl, String middleUrl, String endpoints, String bearerToken) {
		response = RestAssured.given(Specifiactions.Requestspeci(baseUrl, middleUrl)).body(requestFormat)
				.header("Authorization", "Bearer " + bearerToken).when().post(endpoints).then()
				.spec(Specifiactions.responsespeci()).extract().response();
		return this;
	}

	public Method Get(String baseUrl, String middleUrl, String endpoints) {
		response = RestAssured.given(Specifiactions.Requestspeci(baseUrl, middleUrl))
				.when().get(endpoints).then()
				.spec(Specifiactions.responsespeci()).extract().response();
		return this;
	}

	public Method Put(String requestFormat, String baseUrl, String middleUrl, String endpoints) {
		response = RestAssured
				.given(Specifiactions.Requestspeci(baseUrl, middleUrl)).body(requestFormat)
				.when().put(endpoints)
				.then().spec(Specifiactions.responsespeci()).extract().response();
		return this;
	}

	public Method MultiPartData(String baseUrl, String middleUrl, String endpoints, String fileNamePath,
			String subfolder) {
		response = RestAssured.given(Specifiactions.Requestspeci(baseUrl, middleUrl)).accept(ContentType.JSON)
				.contentType(ContentType.MULTIPART).formParam("emailUserInput", subfolder)
				.multiPart("files", new File(fileNamePath))
				.when().post(endpoints)
				.then().spec(Specifiactions.responsespeci()).extract().response();
		return this;
	}

	public Method MultiPartData(String baseUrl, String middleUrl, String endpoints, String fileNamePath) {
		response = RestAssured.given(Specifiactions.Requestspeci(baseUrl, middleUrl)).accept(ContentType.JSON)
				.contentType(ContentType.MULTIPART).multiPart("files", new File(fileNamePath))
				.when().post(endpoints)
				.then().spec(Specifiactions.responsespeci()).extract().response();
		return this;
	}

	public Method MultiPartDataFormultipleFileFromLocal(String baseUrl, String middleUrl, String endpoints, String requestpayload,
			String FilePath) {

		Response response = RestAssured
				.given(Specifiactions.RequestSpecificationMultipleFiles(baseUrl, middleUrl, FilePath))
				.accept(ContentType.JSON)
				.contentType(ContentType.MULTIPART)
				.formParam("emailUserInput", requestpayload)
				.when().post(endpoints)
				.then().spec(Specifiactions.responsespeci()).extract().response();

		return this;

	}

	public Method assertIt(String key, Object val, ValidatorOperation operation) {

		switch (operation.toString()) {
		case "EQUALS":
			response.then().body(key, equalTo(val));
			break;

		case "KEY_PRESENTS":
			response.then().body(key, hasKey(key));
			break;

		case "HAS_ALL":
			break;

		case "NOT_EQUALS":
			response.then().body(key, not(equalTo(val)));
			break;

		case "EMPTY":
			response.then().body(key, empty());
			break;

		case "NOT_EMPTY":
			response.then().body(key, not(emptyArray()));
			break;

		case "NOT_NULL":
			response.then().body(key, notNullValue());
			break;

		case "HAS_STRING":
			response.then().body(key, containsString((String) val));
			break;

		case "SIZE":
			response.then().body(key, hasSize((int) val));
			break;
		}

		return this;
	}

	public void assertIt(List<List<Object>> data) {
		for (List<Object> li : data) {
			switch (((ValidatorOperation) li.get(2)).toString()) {
			case "EQUALS":
				response.then().body(((String) li.get(0)), equalTo((String) li.get(1)));
				break;

			case "KEY_PRESENTS":
				response.then().body(((String) li.get(0)), hasKey((String) li.get(1)));
				break;

			case "HAS_ALL":
				break;
			}
		}
	}

	public Method assertIt(int code) {

		response.then().statusCode(code);

		return this;
	}

	public String extractString(String path) {
		return response.jsonPath().getString(path);
	}

	public int extractInt(String path) {
		return response.jsonPath().getInt(path);
	}

	public List<?> extractList(String path) {
		return response.jsonPath().getList(path);
	}

	public Object extractIt(String path) {
		return response.jsonPath().get(path);
	}

	public String extractHeader(String header_name) {
		return response.header(header_name);
	}

	public String getResponseString() {
		return response.asString();
	}

	public void printResp() {
		response.print();
	}

	public String getCookieValue(String cookieName) {
		return response.getCookie(cookieName);
	}

	public Method assertIt(int code, int optionalCode) {
		response.then().statusCode(anyOf(equalTo(code), equalTo(optionalCode)));
		return this;
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public Headers getAllHeaders() {
		return response.getHeaders();
	}

}
