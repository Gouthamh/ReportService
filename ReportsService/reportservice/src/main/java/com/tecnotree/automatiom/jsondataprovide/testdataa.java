package com.tecnotree.automatiom.jsondataprovide;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class testdataa {

    public static Object[][] getData1(String Filepath, String jsonpath,String requestValue) throws FileNotFoundException {
        JsonElement jsonData = JsonParser.parseReader(new FileReader(Filepath));
        JsonElement dataSet = jsonData.getAsJsonObject().get(jsonpath);
        JsonArray jsonArray = dataSet.getAsJsonArray();

        Object[][] result = new Object[jsonArray.size()][2]; // 2 columns: JSON and templateName

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            String jsonStr = jsonObject.toString();
            String templateName = jsonObject.get(requestValue).getAsString();

            result[i][0] = jsonStr;
            result[i][1] = templateName;
        }

        return result;
    }

    @DataProvider(name = "getData")
    public Object[][] dataProvider() throws FileNotFoundException {
        return getData1("BulkConfigu.json", "dataSet","templateName");
    }

    @Test(dataProvider = "getData")
    public void dataProviders(String json, String templateName) {
        System.out.println(json);
        System.out.println(templateName);
        System.out.println("------------");
    }
    
    
//    public static 	void main(String args[]) throws FileNotFoundException {
//      getData1("BulkConfigu.json", "dataSet");
//
//    }
}

