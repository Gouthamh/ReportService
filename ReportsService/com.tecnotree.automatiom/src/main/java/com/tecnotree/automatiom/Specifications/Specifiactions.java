package com.tecnotree.automatiom.Specifications;

import java.io.File;
import java.util.List;

import org.testng.Reporter;

import com.tecnotree.automatiom.utilities.Fileupload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifiactions {

	static RequestSpecBuilder requestSpecBulider;

	public static RequestSpecification Requestspeci(String BaseUrl, String MiddleUrl) {
		requestSpecBulider = new RequestSpecBuilder().setBaseUri(BaseUrl).setBasePath(MiddleUrl)
				.setContentType(ContentType.JSON).log(LogDetail.ALL);

		return requestSpecBulider.build();

	}

	public static RequestSpecification addFilesToRequest(RequestSpecification builder,String FilePath) {

		List<String> a = Fileupload.getFileList(FilePath);
		for (String s : a) {
			builder.multiPart("files", new File(s));
			requestSpecBulider.addMultiPart("files", new File(s));
		}

		return requestSpecBulider.build();
	}

	public static RequestSpecification RequestSpecificationMultipleFiles(String baseUrl, String middleUrl,String FilePath) {
		RequestSpecification builder = Requestspeci(baseUrl, middleUrl);
		builder = addFilesToRequest(builder,FilePath);
		return builder;
	}

	public static ResponseSpecification responsespeci() {
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()

				.log(LogDetail.ALL);
		Reporter.log(LogDetail.ALL.toString());

		return responseSpecBuilder.build();

	}
}
