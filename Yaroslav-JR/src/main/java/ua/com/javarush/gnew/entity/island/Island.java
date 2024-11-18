package ua.com.javarush.gnew.entity.island;

import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Random;

public class Island {

    private final int rows = 1000;
    private final int cols = 1000;
    private final Cell[][] field;

    public Island() {
        field = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = new Cell();
            }
        }
    }

}
