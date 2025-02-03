package com.tecnotree.automatiom.utilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ExtractFileName {
	
	public static String FileName(String fileUrl) throws MalformedURLException, URISyntaxException {
		//String fileUrl = "https://minio.tecnotree.com/dap-reports-service/Default/report/StartaCanada_1722404127924.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=P8fRJleOzQKPnVy1%2F20240731%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240731T053534Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=9c5536e17f06cf03df4e2abe766b9bd7d218bc093fdbd4eadbc9c80b9d1a38d1";
		
		    URL url = new URL(fileUrl);
	        URI uri = url.toURI();
	        String path = uri.getPath();
	        int s=path.lastIndexOf('/');
	        return path.substring(s+1);
		
	}
	
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		FileName(null);
	}

}
