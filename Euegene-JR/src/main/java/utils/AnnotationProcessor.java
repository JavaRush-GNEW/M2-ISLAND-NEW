package utils;

import annotations.LoadProperties;
import com.fasterxml.jackson.databind.JsonNode;
import entities.Animal;

import java.lang.reflect.Field;

public class AnnotationProcessor {
    public static void initializeFromJSON(Class obj) {
        JsonNode properties = JSONLoader.getProperties(((Class<?>) obj).getSimpleName());

        if (properties == null) {
            System.err.println("No properties found for class: " + ((Class<?>) obj).getSimpleName());
            return;
        }

        for (Field field : ((Class<?>) obj).getDeclaredFields()) {
            if (field.isAnnotationPresent(LoadProperties.LoadFromJSON.class)) {
                LoadProperties.LoadFromJSON annotation = field.getAnnotation(LoadProperties.LoadFromJSON.class);
                String key = annotation.key();

                if (properties.has(key)) {
                    field.setAccessible(true);
                    try {
                        if (field.getType() == int.class) {
                            field.setInt(obj, properties.get(key).asInt());
                        } else if (field.getType() == double.class) {
                            field.setDouble(obj, properties.get(key).asDouble());
                        }
                        // Додайте інші типи за потреби
                    } catch (IllegalAccessException e) {
                        System.err.println("Failed to set field: " + field.getName());
                    }
                }
            }
        }
    }
}

