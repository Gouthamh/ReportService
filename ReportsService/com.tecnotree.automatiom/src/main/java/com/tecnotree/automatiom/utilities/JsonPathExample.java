package com.tecnotree.automatiom.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.List;

public class JsonPathExample {
    public static void main(String[] args) throws IOException {
        // Sample JSON
        String json = "{ \"store\": { \"book\": [{ \"title\": \"Book 1\", \"author\": \"Author 1\" },{ \"title\": \"Book 2\", \"author\": \"Author 2\" }] } }";

        // JSONPath expression
        String jsonPathExpression = "$.store.book[*]";

        // Parse JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        System.out.println(rootNode);

        // Apply JSONPath
        Configuration conf = Configuration.builder().build();
        List<Object> matchingNodes = JsonPath.using(conf).parse(json).read(jsonPathExpression);

        // Print matching JSON objects
        for (Object node : matchingNodes) {
            System.out.println(node.toString());
        }
    }
}
