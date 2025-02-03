package com.tecnotree.automatiom.Specifications;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import com.tecnotree.automatiom.utilities.Fileupload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifiactions {

	private static final Logger logger = LogManager.getFormatterLogger(Specifiactions.class);

	static RequestSpecBuilder requestSpecBulider;

	public static RequestSpecification Requestspeci(String BaseUrl, String MiddleUrl) {
		logger.info("Creating RequestSpecification with BaseUrl: {} and MiddleUrl: {}", BaseUrl, MiddleUrl);

		requestSpecBulider = new RequestSpecBuilder().setBaseUri(BaseUrl).setBasePath(MiddleUrl)
				.setContentType(ContentType.JSON);
		
		logger.info("RequestSpecification created with the following details:");
		logger.info("requestInfo :",LogDetail.ALL);
		logger.debug("Base URI: {}", BaseUrl);
		logger.debug("Base Path: {}", MiddleUrl);
		logger.debug("Content Type: {}", ContentType.JSON);

		return requestSpecBulider.build();

	}

	public static RequestSpecification addFilesToRequest(RequestSpecification builder, String FilePath) {

		logger.info("Adding files to request with FilePath: {}", FilePath);

		List<String> a = Fileupload.getFileList(FilePath);
		logger.debug("Files to upload: {}", a);

		for (String s : a) {
			builder.multiPart("files", new File(s));
			logger.debug("Adding file to request: {}", s);

			requestSpecBulider.addMultiPart("files", new File(s));
		}
		logger.info("Completed adding files to request.");

		return requestSpecBulider.build();
	}

	public static RequestSpecification RequestSpecificationMultipleFiles(String baseUrl, String middleUrl,
			String FilePath) {
		logger.info("Creating RequestSpecification with baseUrl: {}, middleUrl: {}, filePath: {}", baseUrl, middleUrl,
				FilePath);

		RequestSpecification builder = Requestspeci(baseUrl, middleUrl);
		logger.debug("Initial RequestSpecification created with base URL: {} and path: {}", baseUrl, middleUrl);

		builder = addFilesToRequest(builder, FilePath);
		logger.debug("Files added to RequestSpecification from path: {}", FilePath);
		logger.info("RequestSpecification with multiple files created successfully.");

		return builder;
	}

	public static ResponseSpecification responsespeci() {
		logger.info("Creating ResponseSpecification with logging detail: {}", LogDetail.ALL);

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

		logger.info("ResponseSpecification created successfully.");

		return responseSpecBuilder.build();

	}
}
