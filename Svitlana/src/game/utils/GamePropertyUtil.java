package game.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import game.entity.GameProperty;
import java.io.File;
import java.io.IOException;

public class GamePropertyUtil {

    public static final String PATH_TO_FILE = "C:\\Java\\Projects\\M2-ISLAND\\M2-ISLAND-Svitlana\\Svitlana\\src\\resources\\";

    public static GameProperty readGameProp(String fileName) throws IOException {
        return new XmlMapper().readValue(new File(PATH_TO_FILE + fileName), GameProperty.class);
    }
}
