package org.ua.com.javarush.gnew.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {
    @Getter
    private static AppConfig config;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream("IslandConfig.yaml");
            if (inputStream == null) {
                throw new RuntimeException("Не найден файл");
            }
                config = mapper.readValue(inputStream, AppConfig.class);
            } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке YAML конфигурации", e);
        }
    }

}
