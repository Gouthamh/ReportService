package com.tecnotree.automatiom.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Fileupload {

	public static String readFileContent(String filename) throws IOException {
		Path filePath = Paths.get(filename);

		if (!Files.exists(filePath)) {
			throw new IOException("File not found: " + filename);
		}

		byte[] fileBytes = Files.readAllBytes(filePath);
		return new String(fileBytes);
	}

	public static List<String> getFileList(String FilePath) {

		String directoryPath = System.getProperty("user.dir") + "/" + FilePath;
		File directory = new File(directoryPath);
		String[] fileNames = directory.list();
		String[] arrays = new String[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			arrays[i] = FilePath + "/" + fileNames[i];
		}
		return Arrays.asList(arrays);
	}

	public static boolean getfileexe(String FilePath,String responseFileName) {

		String directoryPath = System.getProperty("user.dir") + "/" + FilePath;
		File directory = new File(directoryPath);
		String[] fileNames = directory.list();
		String responseFileexe=getFileExtension(responseFileName);
		System.out.println(responseFileexe+"--responseFileexe");
		for (int i = 0; i < fileNames.length; i++) {
			if(fileNames[i].equals(responseFileName)) {
				String localFile=getFileExtension(fileNames[i]);
				System.out.println(localFile+"--localFile");
				if(responseFileexe.equals(localFile)) {
					return true;
				}
				
				
				return false;
			}
		}
		return false;
	}
	
	
	private static String getFileExtension(String fileName) {
	    int lastIndex = fileName.lastIndexOf('.');
	    return (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1).toLowerCase();
	}

	public static void main(String args[]) throws IOException {

//    	Fileupload f= new Fileupload();
//    	System.out.println(Fileupload.readFileContent("NotificationRequestBody/TriggerNotification.json"));

		boolean a = getfileexe("Documents","StartaCanada_1722932131260.zip");
		System.out.println(a);
	

	}
}
