package org.ua.com.javarush.gnew.Config;

import lombok.Getter;

@Getter
public class AppConfig {
    private IslandConfig island;

    @Getter
    public static class IslandConfig {
        private int width;
        private int height;
        private int growGrassCount;
        private int startGrassAmountPerCell;
    }
}
