package com.tecnotree.automatiom.Methods;


import io.restassured.response.Response;
import java.io.File;
import java.util.List;

import com.tecnotree.automatiom.routers.ValidatorOperation;

public interface InterfaceMethods {

    Method Post(String requestFormat, String baseUrl, String middleUrl, String endpoints);
    
    Method Post(String requestFormat, String baseUrl, String middleUrl, String endpoints, String bearerToken);

    Method Get(String baseUrl, String middleUrl, String endpoints);

    Method Put(String requestFormat, String baseUrl, String middleUrl, String endpoints);
    
    Method MultiPartData(String baseUrl, String middleUrl, String endpoints, String fileNamePath, String subfolder);

    Method MultiPartData(String baseUrl, String middleUrl, String endpoints, String fileNamePath);
    
	public Object assertIt(int code);

	public Object assertIt(String key, Object val, ValidatorOperation operation);

	public void assertIt(List<List<Object>> data);

	public void failTest(String expected, String present);

	public void failTest(String message);

	public String extractString(String path);

	public int extractInt(String path);

	public List extractList(String path);

	public Object extractIt(String path);

	public String extractHeader(String header_name);

	public String getResponseString();

	public void printResp();
}
