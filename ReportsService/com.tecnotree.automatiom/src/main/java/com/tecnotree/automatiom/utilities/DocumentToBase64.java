package com.tecnotree.automatiom.utilities;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Base64;

import com.tecnotree.automatiom.routers.SFTP_Info;

public class DocumentToBase64 {

	public static String converterToBase64(InputStream filePath) throws IOException {
		int c = 0;
		InputStreamReader inputStreamReader = new InputStreamReader(filePath);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((c = reader.read()) != -1) {
			bos.write(c);
		}
		byte[] fileByte = bos.toByteArray();
		String base64encoder = Base64.getEncoder().encodeToString(fileByte);
		System.out.println("base64encoder--->" + base64encoder);
		return base64encoder;
	}

	public static String converterToBase64(String filePath) throws IOException {

		BufferedReader objReader = null;
		int c = 0;
		objReader = new BufferedReader(new FileReader(filePath));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		while ((c = objReader.read()) != -1) {
			bos.write(c);
		}
		byte[] fileByte = bos.toByteArray();
		String base64encoder = Base64.getEncoder().encodeToString(fileByte);
		System.out.println("base64encoder--->" + base64encoder);

		return base64encoder;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String basefile64 = converterToBase64("Documents/BPMN.bpmn");
		// System.out.println("---------------------------------------------------------------------------");
		String sftpfile64 = File_upload_services.SFTPFileSftpbase64conversion(SFTP_Info.sftpUserName, SFTP_Info.sftpPasswordName,
				SFTP_Info.sftp_Host_172_20_21_57, SFTP_Info.sftp_Port_31703, SFTP_Info.sshKey_172_20_21_57,
				SFTP_Info.sftppath);
		System.out.println(basefile64.equals(sftpfile64));
	}

}
