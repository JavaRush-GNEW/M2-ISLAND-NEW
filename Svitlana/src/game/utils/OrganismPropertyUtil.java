package game.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import game.entity.Organism;
import game.entity.OrganismProperty;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class OrganismPropertyUtil {
    public static final String PATH_TO_FILE = "C:\\Java\\Projects\\M2-ISLAND\\M2-ISLAND-Svitlana\\Svitlana\\src\\resources\\";
    public static final String EXTENSION = ".json";

    public static OrganismProperty readOrganismProp(Class<? extends Organism> aClass) throws IOException {
        String className = aClass.getSimpleName().toLowerCase();
        return new ObjectMapper().readValue(new File(PATH_TO_FILE + className + EXTENSION), OrganismProperty.class);
    }

    public static Map<String, Integer> readNutritionInf(String fileName) throws IOException {
        return new YAMLMapper().readValue(new File(PATH_TO_FILE + fileName), Map.class);
    }
}
