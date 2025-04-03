package com.tecnotree.automatiom.JsonFomation;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;

public class ReportService {

    public static String Report() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("date", "2024-02-01");
        parameters.put("dateandtime", "2024-02-01");
        parameters.put("interger", 23456);
        parameters.put("restURL", true);

        Map<String, Object> data = new HashMap<>();
        data.put("templateName", "StartaCanada");
        data.put("attachmentType", "CSV");
        data.put("parameters", parameters);
        data.put("password", 234567);
        data.put("zip", true);

        JSONObject jsonObject = new JSONObject(data);

        return jsonObject.toString();  
    }
    
    public static void main(String[] args) {
		System.out.println(Report());
	}
}
