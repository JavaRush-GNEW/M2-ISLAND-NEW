package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JSONLoader {
    private static final HashMap<String, JsonNode> propertiesCache = new HashMap<>();

    public static void load(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream resourceStream = JSONLoader.class.getClassLoader().getResourceAsStream(filePath)) {
            JsonNode rootNode = objectMapper.readTree(resourceStream);
            if (!rootNode.isObject()) {
                throw new IllegalArgumentException("Invalid JSON format: Root node must be an object");
            }

            rootNode.fieldNames().forEachRemaining(className -> {
                JsonNode classProperties = rootNode.get(className);
                if (propertiesCache.putIfAbsent(className, classProperties) != null) {
                    System.err.println("Duplicate key found: " + className + ". Overwriting is not allowed.");
                }
            });
        }
    }

    public static JsonNode getProperties(String className) {
        return propertiesCache.get(className);
    }


}


