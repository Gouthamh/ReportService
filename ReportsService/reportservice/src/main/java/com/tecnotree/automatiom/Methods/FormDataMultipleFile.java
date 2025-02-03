package com.tecnotree.automatiom.Methods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tecnotree.automatiom.Specifications.Specifiactions;
import com.tecnotree.automatiom.utilities.Fileupload;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FormDataMultipleFile {

	public static RequestSpecification addFilesToRequest(RequestSpecification request,String FilePath) {

		List<String> a = Fileupload.getFileList(FilePath);
		for (String s : a) {
			request.multiPart("files", new File(s));
		}

		return request;
	}

	public static Response MultiPartData111(String baseUrl, String middleUrl, String endpoints,
			String requestpayload,String FilePath) {
		
		Response response = RestAssured
				.given(Specifiactions.RequestSpecificationMultipleFiles(baseUrl, middleUrl,FilePath))
				.accept(ContentType.JSON)
				.contentType(ContentType.MULTIPART)
				.formParam("emailUserInput", requestpayload)
				.when()
				.post(endpoints)
				.then()
				.spec(Specifiactions.responsespeci())
				.extract()
				.response();
				
		
		
				return response;

		   

		
	}

	public void name() {

	}

	public static void main(String[] args) {
		List<String> list = new ArrayList();

	}

}
