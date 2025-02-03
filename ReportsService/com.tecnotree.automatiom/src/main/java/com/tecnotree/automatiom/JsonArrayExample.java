package com.tecnotree.automatiom;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonArrayExample {
    public static void main(String[] args) {
        String jsonArrayString = "{\r\n"
        		+ "    \"TimeStamp\": {\r\n"
        		+ "        \"response\": [\r\n"
        		+ "            {\r\n"
        		+ "                \"String\": \"data is inserted on this date and time\",\r\n"
        		+ "                \"time\": \"2024-01-04 15:07:58\"\r\n"
        		+ "            },\r\n"
        		+ "            {\r\n"
        		+ "                \"String\": \"data is inserted on this date and time\",\r\n"
        		+ "                \"time\": \"2024-01-04 15:11:04\"\r\n"
        		+ "            },\r\n"
        		+ "            {\r\n"
        		+ "                \"String\": \"data is inserted on this date and time\",\r\n"
        		+ "                \"time\": \"2024-01-08 19:52:31\"\r\n"
        		+ "            },\r\n"
        		+ "            {\r\n"
        		+ "                \"String\": \"data is inserted on this date and time\",\r\n"
        		+ "                \"time\": \"2024-01-08 19:52:44\"\r\n"
        		+ "            },\r\n"
        		+ "            {\r\n"
        		+ "                \"String\": \"data is inserted on this date and time\",\r\n"
        		+ "                \"time\": \"2024-01-08 19:52:50\"\r\n"
        		+ "            }\r\n"
        		+ "        ]\r\n"
        		+ "    }\r\n"
        		+ "}";
        JSONArray jsonArray = new JSONArray(jsonArrayString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("TimeStamp.response[0].String");
        

            System.out.println("Name: " + name);
        }
    }
}