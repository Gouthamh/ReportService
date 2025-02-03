package com.tecnotree.automatiom.jsondataprovide;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonValuesreads {

    @Test(dataProvider = "getData")
    public void testMethod(TestData data) {
        System.out.println(data);
    }

    @DataProvider
    public Object[] getData() throws FileNotFoundException {

        JsonElement jsonData = new JsonParser().parse(new FileReader("BulkConfigu.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
            
        }
        return returnValue;
    }
}