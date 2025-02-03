package com.tecnotree.automatiom.reusedmethod;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.tecnotree.automatiom.utilities.ExtractFileName;
import com.tecnotree.automatiom.utilities.FileDownloaders;
import com.tecnotree.automatiom.utilities.FileService;

import io.restassured.response.Response;

public class upanddown {
	FileService fileService;
	ExtractFileName urlService;
	long intialcount;
	FileDownloaders fileDownloader;

	
	public void tearup() {
		fileService = new FileService();
        urlService = new ExtractFileName();
         fileDownloader = new FileDownloaders(fileService, urlService);
		fileDownloader.deleteAllFiles();
		intialcount = fileDownloader.countFiles();
	}
	
	public void teardown(Response response) {
		
        Assert.assertEquals(response.statusCode(), 200,"Status_Code_200");
		Assert.assertEquals(response.jsonPath().getString("type"), "Success","type_response_success");
		Assert.assertEquals(response.jsonPath().getInt("status"), 200,"response_code_200");
		fileDownloader.downloadFile(response.jsonPath().getString("ATTACHMENT_URL[0]"));
		fileDownloader.countFiles();
		long finalcount = fileDownloader.countFiles();
		Assert.assertEquals(intialcount, finalcount-1,"inserted files");
	}

}
