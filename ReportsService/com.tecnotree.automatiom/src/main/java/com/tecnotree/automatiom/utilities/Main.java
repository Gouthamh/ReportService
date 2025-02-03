package com.tecnotree.automatiom.utilities;

public class Main {
	
    public static void main(String[] args) {
        FileService fileService = new FileService();
        ExtractFileName urlService = new ExtractFileName();
        FileDownloaders fileDownloader = new FileDownloaders(fileService, urlService);

        String fileUrl = "https://minio.tecnotree.com/dap-reports-service/Default/report/DemoMongo_name_1722511737143.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=P8fRJleOzQKPnVy1%2F20240801%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240801T112903Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=c7e3dc8e5560ce19c06096ddecd56b57deb5a28896b66ed88318d0206ba9c32b";
        // Download the file
        fileDownloader.downloadFile(fileUrl);
        
        System.out.println(fileDownloader.countFiles());
       

        // Optionally delete the file
        //fileDownloader.deleteFile(fileUrl);

        // Optionally delete all files in the directory
        //fileDownloader.deleteAllFiles();
    }
}
