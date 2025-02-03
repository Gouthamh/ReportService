package com.tecnotree.automatiom.SS0_aaccess_token;

import java.io.InputStream;
import java.util.Properties;

public class Readfromapplicationprpoerities {
	
	public static String application(String Path,String keyname) {
		String property1 = null;
		Properties properties = new Properties();

		try (InputStream input = Readfromapplicationprpoerities.class.getClassLoader()
				.getResourceAsStream(Path)) {
			if (input == null) {
				// System.out.println("Sorry, unable to find application.properties");
				System.out.println("Sorry, unable to find application.properties");
			}
			properties.load(input);
			property1 = properties.getProperty(keyname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return property1;
	}

	public static void main(String[] args) {

		String name = application("Application.properties","client_secret");
		System.out.println(name);

	}

}
