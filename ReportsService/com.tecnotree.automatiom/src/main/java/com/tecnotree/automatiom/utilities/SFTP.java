package com.tecnotree.automatiom.utilities;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.tecnotree.automatiom.routers.SFTP_Info;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

public class SFTP {

	public static Session connectToSFTP(String sftpHost, int sftpPort, String sftpUserName, String sftpPassword,
			String sshKey) throws JSchException {
		JSch jsch = new JSch();
		Session session =null;

		if (sftpPort == 22) {
			jsch.setKnownHosts(new ByteArrayInputStream(sshKey.getBytes()));
			session = jsch.getSession(sftpUserName, sftpHost, sftpPort);// NOSONAR
			session.setConfig("server_host_key", "ssh-rsa");
			session.setPassword(sftpPassword);
			System.out.println("SFTP connecting....!");
			session.connect();
			System.out.println("SFTP connected....!");
			return session;
		} else {
			jsch.setKnownHosts(new ByteArrayInputStream(sshKey.getBytes()));
			session = jsch.getSession(sftpUserName, sftpHost, sftpPort);
			//session.setConfig("server_host_key", "ssh-rsa");
			session.setPassword(sftpPassword);
			session.connect();
			System.out.println("SFTP connected....!");
			return session;
		}
	}

	public static ChannelSftp setupSFTPChannel(Session session, String sftpPath) throws Exception {
		ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		try {
			System.out.println("alreaded created...");
			channelSftp.cd(sftpPath);

		} catch (SftpException e) {
            throw new SftpException(e.id, "Path not found: " + sftpPath);
		}
		
		return channelSftp;
	}
	

	public static int SFTPCount(String sftpHost, int sftpPort, String sftpUserName, String sftpPassword, String sshkey,
			String sfthpath) {

		int fileCount = -1;
		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshkey);
			ChannelSftp channelSftp = setupSFTPChannel(session, sfthpath);
			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sfthpath);
			fileCount = ls.size() - 2; // subtract 2 for '.' and '..' entries
			System.out.println("File count: " + fileCount);
			channelSftp.exit();
			session.disconnect();
			return fileCount;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileCount;

	}

	public static void sftpfileexections(String sftpHost, int sftpPort, String sftpUserName, String sftpPassword,
			String sshkey, String sfthpath, String file) {
		{
			try {
				List<String> filesexc = new ArrayList<String>();
				String fileExtension = null;
				Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshkey);

				ChannelSftp channelSftp = setupSFTPChannel(session, sfthpath);

				Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sfthpath);

				for (ChannelSftp.LsEntry entry : ls) {
					SftpATTRS attrs = entry.getAttrs();
					if (attrs.getPermissionsString().equals("-rw-r--r--")) {
						String filename = entry.getFilename();
						fileExtension = filename.substring(filename.lastIndexOf("."));
						filesexc.add(fileExtension);
					}
				}
				System.out.println("filesexc-->" + filesexc);

				if (filesexc.stream().distinct().count() == 1 && !filesexc.isEmpty()
						&& file.equals(filesexc.toString())) {
					System.out.println("true--->");

				} else {
					System.out.println("false--->");
				}

				channelSftp.disconnect();
				session.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void sftpremove(String sftpHost, int sftpPort, String sftpUserName, String sftpPassword,
			String sshkey, String sfthpath) {

		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshkey);

			System.out.println("Connected to SFTP server");

			ChannelSftp channelSftp = setupSFTPChannel(session, sfthpath);

			System.out.println("Changed directory to " + sfthpath);

			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sfthpath);
			int i = 0;

			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = entry.getAttrs();
				String permissions = attrs.getPermissionsString();
				//System.out.println("permissions--->"+permissions +"  filename"+entry.getFilename());
				

				if (!permissions.equals("drwxr-xr-x") && !permissions.equals("drwxrwxrwx") && !permissions.equals("drwx------")&& !permissions.equals("drwxrwxr-x")) {
					String filename = entry.getFilename();
					i++;
					//System.out.println("permissions--->"+permissions);
					//System.out.println("permissions--->"+permissions +"  filename"+entry.getFilename());

					System.out.println("Deleting file: " + filename + " ,count : " + i);
					channelSftp.rm(sfthpath + filename);
				}
				else {
					System.out.println("---not");
				}
			}

			channelSftp.exit();
			session.disconnect();
			System.out.println("Disconnected from SFTP server");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String headers(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort, String sshKey,
			String sftpPath) {

		String line;
		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = setupSFTPChannel(session, sftpPath);

			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sftpPath);
			List<Integer> lineCounts = new ArrayList<>();

			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = channelSftp.lstat(sftpPath + entry.getFilename());
				String permissions = attrs.getPermissionsString();

				if (permissions.equals("-rw-r--r--")) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(channelSftp.get(sftpPath + entry.getFilename())));

					int lineCount = 0;

					while ((line = reader.readLine()) != null) {
						lineCount++;
						return line;
					}
					// return line;
					lineCounts.add(lineCount);
				}
			}

			channelSftp.exit();
			session.disconnect();

			// System.out.println("Line counts: " + lineCounts);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static int frequencyOfOccurrenceForEachLineCount(String sftpUserName, String sftpPassword, String sftpHost,
			int sftpPort, String sshKey, String sftpPath) {
		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = setupSFTPChannel(session, sftpPath);

			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sftpPath);

			List<Integer> lineCounts = new ArrayList<>();
			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = channelSftp.lstat(sftpPath + entry.getFilename());
				String sftpPermission = attrs.getPermissionsString();
				if (sftpPermission.equals("-rw-r--r--")) {
					InputStream inputStream = channelSftp.get(sftpPath + entry.getFilename());
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					int lines = 0;
					while (reader.readLine() != null) {
						lines++;
					}
					lineCounts.add(lines);
				}
			}

			Collections.sort(lineCounts);
			System.out.println("Sorted line counts: " + lineCounts);

			Map<Integer, Integer> lineCountFrequency = new HashMap<>();
			for (int key : lineCounts) {
				lineCountFrequency.put(key, lineCountFrequency.getOrDefault(key, 0) + 1);
			}

			int maxFrequency = 0;
			for (int frequency : lineCountFrequency.values()) {
				if (frequency > maxFrequency) {
					maxFrequency = frequency;
				}
			}

			channelSftp.exit();
			session.disconnect();

			return maxFrequency;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static String readAllLines(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort,
			String sshKey, String sftpPath) {

		List<String> lines = new ArrayList<>();

		try {
			JSch jsch = new JSch();
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = setupSFTPChannel(session, sftpPath);

			Vector<ChannelSftp.LsEntry> files = channelSftp.ls("*");
			for (ChannelSftp.LsEntry entry : files) {
				if (entry.getAttrs().isDir()) {
					continue; // Skip directories
				}

				if (!entry.getAttrs().getPermissionsString().equals("-rw-r--r--")) {
					continue; // Skip files with different permissions
				}

				InputStream inputStream = null;
				BufferedReader reader = null;
				try {
					inputStream = channelSftp.get(entry.getFilename());
					reader = new BufferedReader(new InputStreamReader(inputStream));

					String line;

					while ((line = reader.readLine()) != null) {
						lines.add(line);
					}
				} finally {
					if (reader != null) {
						reader.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
				}
			}

			channelSftp.disconnect();
			session.disconnect();

			// Convert the lines list to a single string
			StringBuilder sb = new StringBuilder();
			for (String line : lines) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static boolean Header_is_present(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort,
			String sshKey, String sftpPath, String headers) {

		List<String> lines = new ArrayList<>();

		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = setupSFTPChannel(session, sftpPath);

			Vector<ChannelSftp.LsEntry> files = channelSftp.ls("*");
			for (ChannelSftp.LsEntry entry : files) {
				if (entry.getAttrs().isDir()) {
					continue; // Skip directories
				}

				if (!entry.getAttrs().getPermissionsString().equals("-rw-r--r--")) {
					continue; // Skip files with different permissions
				}

				InputStream inputStream = null;
				BufferedReader reader = null;
				try {
					inputStream = channelSftp.get(entry.getFilename());
					reader = new BufferedReader(new InputStreamReader(inputStream));

					String line;

					while ((line = reader.readLine()) != null) {
						lines.add(line);
					}
				} finally {
					if (reader != null) {
						reader.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
				}
			}

			channelSftp.disconnect();
			session.disconnect();

			// Convert the lines list to a single string
			StringBuilder sb = new StringBuilder();
			for (String line : lines) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();
			System.out.println(result);

			if (result.contains(headers)) {
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean SFTPFileExtensionValidator(String sftpUserName, String sftpPassword, String sftpHost,
			int sftpPort, String sshKey, String sftpPath, String file) {

		List<String> filesexc = new ArrayList<>();
		String fileExtension = null;
		boolean allEqual = false;

		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = setupSFTPChannel(session, sftpPath);

			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sftpPath);

			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = entry.getAttrs();
				if (attrs.getPermissionsString().equals("-rw-r--r--")) {
					String filename = entry.getFilename();
					fileExtension = filename.substring(filename.lastIndexOf("."));
					filesexc.add(fileExtension);
				}
			}
			System.out.println("filesexc-->" + filesexc);

			if (filesexc == null || filesexc.size() == 0) {
				return true;
			}

			for (int i = 0; i < filesexc.size(); i++) {

				if (!filesexc.get(i).equals(file)) {
					return false; // If any element is different, return false
				}
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allEqual;
	}

	public static int SftpFileCount(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort,
			String sshKey, String sftpPath) {
		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();

			System.out.println("-----pwd---" + channelSftp.pwd());
			// channelSftp.mkdir("/data/");

			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sftpPath);

			List<Integer> lineCounts = new ArrayList<>();
			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = entry.getAttrs();
				String sftpPermission = attrs.getPermissionsString();
				if (sftpPermission.equals("-rw-r--r--")) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(channelSftp.get(sftpPath + entry.getFilename())));
					int lines = 0;
					while (reader.readLine() != null) {
						lines++;
					}
					lineCounts.add(lines);
				}
			}

			System.out.println(lineCounts.toString());
			System.out.println("------->" + lineCounts.size());
			return lineCounts.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int SftpAllFileCount(String sftpUserName, String sftpPassword, String sftpHost, int sftpPort,
			String sshKey, String sftpPath) {
		int i = 0;
		try {
			Session session = connectToSFTP(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey);

			ChannelSftp channelSftp = setupSFTPChannel(session, sftpPath);

			Vector<ChannelSftp.LsEntry> ls = channelSftp.ls(sftpPath);
			List<Integer> lineCounts = new ArrayList<>();

			for (ChannelSftp.LsEntry entry : ls) {
				SftpATTRS attrs = channelSftp.lstat(sftpPath + entry.getFilename());
				String permissions = attrs.getPermissionsString();

				if (permissions.equals("-rw-r--r--")) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(channelSftp.get(sftpPath + entry.getFilename())));
					int lineCount = 0;

					while ((reader.readLine()) != null) {
						// System.out.println("--->" + line);
						lineCount++;
						i++;
					}

					lineCounts.add(lineCount);
				}

			}
			return i;

			// System.out.println("Line counts: " + lineCounts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {

		String sftpHost = "172.20.21.57";
		int sftpPort = 31106;
		String sftpUserName = "file-to-rest";
		String sftpPassword = "admin";
		String sshKey = "172.20.21.57 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCfeK+wXukXepUaNZmSTGEjDeUTmJdJlMepkUZA6m5p6tzJa77dIAEx1DOdS+uYCOQWJeguACk204p8Qg83w2Is/YtfzoprK9suIBuZLgDoFT+rSXWptVR3weHQqmwh9aN3AwcRN2ql+czgT3SjIMOahz9peLeRKe+20TiOLv8+c0h8+IQxkqX7qrY0sYrrZyJsQ9HQVrLGEb9RLmOgyKPnqCDIRFH72+nYSpcN+k7Yxc8GiL+qUtKs/GZwBYW2oQMVJR/7XeFVfMYgKBbh/R4L9hYztJX5jCn6iovAwhMB9bd0KqDHhJsiU39R8JoUeGXkW83j3/dZdO1QvTY+AQHmZRW7mZ5MInWeT6BwAJqYICfibSfD/0FYXG7fonPAlzAl6lDiMyBpmZnZOMn0vF8JgHPu1AUxgPrcbNbLhwl19qfz2GnIHoDfg2h8IPXhASh56oIGFSbREIeTYjx0JkJgkZsbGKMYKnEjbSApMUNfhUu16/khm+/tptDqmMDnYmU2E4SQ+Jici9FD1bGO2pDUS2Z6NQz5HyzQyhHPnSH/eQnowRc+YnXa15npey/EEsKE/NGEtS/xk1DXsqEk30wMZYEfwEga5YDCCwIEgzYpHIgF2MmCGYnb/AgtjPRQEYvAWz42S2yD1FNnmsxGxTvOfg60+PuzJW5l2l6zrzvPxw==";
		//String sshKey = "10.4.3.95 ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAnbaHlbTmBEqzoAGSMQQfBFm7NKYmhyPBPhLGoeHT0t88et9uPbV8lBMMV6+NbzmgwXp+eINNofEG8fYhowJY6EqP3Dy9oBgwhFzxoEMVpFOujuw9rjRHpW4zrqxo0q+cAN5DHNiqXPxLmqF5sEKBMge/9djGHlxfbNuXEi5uoemExpQ+8yd2H1xuMrVHV245EC/BUZYC2zWmccWV2Phg41in4GaKddyTCFDFAaVziNZAMn1RuKMG+Xx8N9I2Cji70ZiWOvZiu8iYH/a4ZVxPxo9OyvsQZUiIuAsVfCPomisCmgTmbZSVivgwC1Q4ZaaBavxt/vv0giZ7yipMzHSOzw==";

		//String sshKey = "172.20.21.227 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDBOFACNqqkQKK8o4LuaOCiogpO0xwTdFpSqB8394dBJWu0LtrYA9LxjMCwBu7oGB4pcRfLvFBV0g+dVDMuq+2PDkvmyXLntnjZkTLQlH2B2dmxenRfB0QZVEqh86fmrUxbhbI0hDYJLx9YFs49vdYorg7zYOMu2fhnpzFoc8Icw907rJP1PVMQWpK0SeMWYLXhCajWm0oq6GmB0Gf0qwb+1SzaHmFyuhTd5jdK6Yk25rTR67R5WT5ElNW2ZoyI2kK/ILBKyvS1DyuhiHm/UJ4AHkA6DKLjfSPgE2JgOHZQ01+EeYYghyjbzeyv+fOehhajE6kqAYjGakrMB4FmGeY/wXgSmaUpNms3phBDNsgEwOi+UinfYxrauhNDWDMZWw/Fl0Lw17rUyczf8I5wwxvAffMUvaFwMVnR5Z3bkb7+iTPxpvRWmQfrlyhU9J+8tM9vUBWLxeg0dIRuWC1TJnh4vG4EHasDa17UFpsdC+vRluYfFeciHHMFKKHXQwJD860=";
		
		String sftppath = "/bulk-store/file-to-rest/";
		String file = "[.gz]";
		
		

//		String sftpHost = "172.30.201.109";
//		int sftpPort = 30040;
//		String sftpUserName = "admin";
//		String sftpPassword = "admin";
//		String sshKey = "[172.30.201.109]:30040 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCfeK+wXukXepUaNZmSTGEjDeUTmJdJlMepkUZA6m5p6tzJa77dIAEx1DOdS+uYCOQWJeguACk204p8Qg83w2Is/YtfzoprK9suIBuZLgDoFT+rSXWptVR3weHQqmwh9aN3AwcRN2ql+czgT3SjIMOahz9peLeRKe+20TiOLv8+c0h8+IQxkqX7qrY0sYrrZyJsQ9HQVrLGEb9RLmOgyKPnqCDIRFH72+nYSpcN+k7Yxc8GiL+qUtKs/GZwBYW2oQMVJR/7XeFVfMYgKBbh/R4L9hYztJX5jCn6iovAwhMB9bd0KqDHhJsiU39R8JoUeGXkW83j3/dZdO1QvTY+AQHmZRW7mZ5MInWeT6BwAJqYICfibSfD/0FYXG7fonPAlzAl6lDiMyBpmZnZOMn0vF8JgHPu1AUxgPrcbNbLhwl19qfz2GnIHoDfg2h8IPXhASh56oIGFSbREIeTYjx0JkJgkZsbGKMYKnEjbSApMUNfhUu16/khm+/tptDqmMDnYmU2E4SQ+Jici9FD1bGO2pDUS2Z6NQz5HyzQyhHPnSH/eQnowRc+YnXa15npey/EEsKE/NGEtS/xk1DXsqEk30wMZYEfwEga5YDCCwIEgzYpHIgF2MmCGYnb/AgtjPRQEYvAWz42S2yD1FNnmsxGxTvOfg60+PuzJW5l2l6zrzvPxw==";
//
//		//String sshKey = "172.20.21.57 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCfeK+wXukXepUaNZmSTGEjDeUTmJdJlMepkUZA6m5p6tzJa77dIAEx1DOdS+uYCOQWJeguACk204p8Qg83w2Is/YtfzoprK9suIBuZLgDoFT+rSXWptVR3weHQqmwh9aN3AwcRN2ql+czgT3SjIMOahz9peLeRKe+20TiOLv8+c0h8+IQxkqX7qrY0sYrrZyJsQ9HQVrLGEb9RLmOgyKPnqCDIRFH72+nYSpcN+k7Yxc8GiL+qUtKs/GZwBYW2oQMVJR/7XeFVfMYgKBbh/R4L9hYztJX5jCn6iovAwhMB9bd0KqDHhJsiU39R8JoUeGXkW83j3/dZdO1QvTY+AQHmZRW7mZ5MInWeT6BwAJqYICfibSfD/0FYXG7fonPAlzAl6lDiMyBpmZnZOMn0vF8JgHPu1AUxgPrcbNbLhwl19qfz2GnIHoDfg2h8IPXhASh56oIGFSbREIeTYjx0JkJgkZsbGKMYKnEjbSApMUNfhUu16/khm+/tptDqmMDnYmU2E4SQ+Jici9FD1bGO2pDUS2Z6NQz5HyzQyhHPnSH/eQnowRc+YnXa15npey/EEsKE/NGEtS/xk1DXsqEk30wMZYEfwEga5YDCCwIEgzYpHIgF2MmCGYnb/AgtjPRQEYvAWz42S2yD1FNnmsxGxTvOfg60+PuzJW5l2l6zrzvPxw==";
//
//		String sftppath = "/UsageEvents/processed/";
//		String file = "[.gz]";

//		long count = SFTPCount(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey, sftppath);
//		System.out.println("---" + count);

//		String sfthpath;
//		sftpfileexections(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey, sftppath, file);
//
//		sftpfileexections(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey, sftppath, file);
//		System.out.println("---" + count);

		// sftpremove(sftpHost, sftpPort, sftpUserName, sftpPassword, sshKey, sftppath);
		//SFTP.sftpremove(Routers.sftpHost_172_20_21_57, Routers.sftpPort_31703, Routers.sftpUserName, Routers.sftpPassword, Routers.sshKey__172_20_21_57, Routers.sftpBasePath);

//		System.out.println(headers(sftpUserName, sftpPassword, sftpHost, sftpPort, sshKey, sftppath));
//
//		System.out.println(frequencyOfOccurrenceForEachLineCount(sftpUserName, sftpPassword, sftpHost, sftpPort, sshKey,
//				sftppath));
//
//		System.out.println(readAllLines(sftpUserName, sftpPassword, sftpHost, sftpPort, sshKey, sftppath));
//
//		System.out.println(Header_is_present(sftpUserName, sftpPassword, sftpHost, sftpPort, sshKey, sftppath,
//				"\"aaaaa\"|\"a\"|\"a\"|\"s\"|\"d\"|\"x\"|\"d\"|\"sd\"|\"s\"|\"s\"|\"s\"|\"\"|\"dd\"|\"d\"|"));
//
		//System.out.println(SFTPFileExtensionValidator(sftpUserName, sftpPassword, sftpHost, sftpPort, sshKey, sftppath, file));
		
System.out.println(SftpFileCount(sftpUserName, sftpPassword, sftpHost, sftpPort, sshKey, sftppath));
//
		//System.out.println(SftpAllFileCount(sftpUserName, sftpPassword, sftpHost, sftpPort, sshKey, sftppath));


	}

}
