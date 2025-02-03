package com.tecnotree.automatiom.utilities;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.tecnotree.automatiom.routers.SFTP_Info;

public class File_upload_services {

	public static String SFTPFileName(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort,
			String sshKey, String sftpPath) {
		String filename = null;

		try {
			Session session = SFTP.connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = SFTP.setupSFTPChannel(session, sftpPath);

			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sftpPath);

			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = entry.getAttrs();
				String permissions = attrs.getPermissionsString();

				if (!permissions.equals("drwxr-xr-x") && !permissions.equals("drwxrwxrwx")) {
					filename = entry.getFilename();
					System.out.println("FileName--->"+filename);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}
	
	public static String SFTPFileSftpbase64conversion(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort,
			String sshKey, String sftpPath) throws IOException {
		String filename = null;
		String Sftpbase64conversion=null;

		try {
			Session session = SFTP.connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = SFTP.setupSFTPChannel(session, sftpPath);
			


			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sftpPath);

			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = entry.getAttrs();
				String permissions = attrs.getPermissionsString();

				if (!permissions.equals("drwxr-xr-x") && !permissions.equals("drwxrwxrwx") && !permissions.equals("drwx------")&& !permissions.equals("drwxrwxr-x")) {
					filename = entry.getFilename();
					System.out.println(filename);
					
					Sftpbase64conversion = DocumentToBase64.converterToBase64(channelSftp.get(sftpPath+entry.getFilename()));

				}
			}
			  
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Sftpbase64conversion;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String sftpHost = "172.20.21.57";
		int sftpPort = 31703;
		String sftpUserName = "admin";
		String sftpPassword = "admin";
		String sshKey = "172.20.21.57 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCfeK+wXukXepUaNZmSTGEjDeUTmJdJlMepkUZA6m5p6tzJa77dIAEx1DOdS+uYCOQWJeguACk204p8Qg83w2Is/YtfzoprK9suIBuZLgDoFT+rSXWptVR3weHQqmwh9aN3AwcRN2ql+czgT3SjIMOahz9peLeRKe+20TiOLv8+c0h8+IQxkqX7qrY0sYrrZyJsQ9HQVrLGEb9RLmOgyKPnqCDIRFH72+nYSpcN+k7Yxc8GiL+qUtKs/GZwBYW2oQMVJR/7XeFVfMYgKBbh/R4L9hYztJX5jCn6iovAwhMB9bd0KqDHhJsiU39R8JoUeGXkW83j3/dZdO1QvTY+AQHmZRW7mZ5MInWeT6BwAJqYICfibSfD/0FYXG7fonPAlzAl6lDiMyBpmZnZOMn0vF8JgHPu1AUxgPrcbNbLhwl19qfz2GnIHoDfg2h8IPXhASh56oIGFSbREIeTYjx0JkJgkZsbGKMYKnEjbSApMUNfhUu16/khm+/tptDqmMDnYmU2E4SQ+Jici9FD1bGO2pDUS2Z6NQz5HyzQyhHPnSH/eQnowRc+YnXa15npey/EEsKE/NGEtS/xk1DXsqEk30wMZYEfwEga5YDCCwIEgzYpHIgF2MmCGYnb/AgtjPRQEYvAWz42S2yD1FNnmsxGxTvOfg60+PuzJW5l2l6zrzvPxw==";
		// String sshKey = "10.4.3.95 ssh-rsa
		// AAAAB3NzaC1yc2EAAAABIwAAAQEAnbaHlbTmBEqzoAGSMQQfBFm7NKYmhyPBPhLGoeHT0t88et9uPbV8lBMMV6+NbzmgwXp+eINNofEG8fYhowJY6EqP3Dy9oBgwhFzxoEMVpFOujuw9rjRHpW4zrqxo0q+cAN5DHNiqXPxLmqF5sEKBMge/9djGHlxfbNuXEi5uoemExpQ+8yd2H1xuMrVHV245EC/BUZYC2zWmccWV2Phg41in4GaKddyTCFDFAaVziNZAMn1RuKMG+Xx8N9I2Cji70ZiWOvZiu8iYH/a4ZVxPxo9OyvsQZUiIuAsVfCPomisCmgTmbZSVivgwC1Q4ZaaBavxt/vv0giZ7yipMzHSOzw==";

		String sftppath = "/bulk-store/";
		String file = "[.gz]";
		
		String path = File_upload_services.SFTPFileName(SFTP_Info.sftpUserName_10_4_3_95_22, SFTP_Info.sftpPassword10_4_3_95_22, SFTP_Info.sftpHost_10_4_3_95, SFTP_Info.sftpPort_22, SFTP_Info.sshKey_10_4_3_95, SFTP_Info.sftppath_10_4_3_95);

		System.out.println(path);
		//String path = SFTPFilePath(sftpUserName,sftpPassword,sftpHost,sftpPort,sshKey,sftppath);
		


	}

}
