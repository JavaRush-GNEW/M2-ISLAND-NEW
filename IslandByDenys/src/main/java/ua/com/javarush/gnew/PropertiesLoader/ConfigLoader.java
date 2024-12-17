package ua.com.javarush.gnew.PropertiesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        loadProperties("animals/Bear.properties");
        loadProperties("animals/Plants.properties");
        loadProperties("animals/Boar.properties");
        loadProperties("animals/Deer.properties");
        loadProperties("animals/Fox.properties");
        loadProperties("animals/Wolf.properties");
        loadProperties("animals/Horse.properties");
        loadProperties("animals/Rabbit.properties");
        loadProperties("animals/Sheep.properties");
    }

    private static void loadProperties(String fileName) {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            } else {
                throw new IOException("Файл " + fileName + " не найден!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static double getDoubleProperty(String key) {
        return Double.parseDouble(properties.getProperty(key));
    }
}
