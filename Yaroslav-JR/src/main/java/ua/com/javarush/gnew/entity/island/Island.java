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

    public void initializeField() {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int grassCount = random.nextInt(91);
                int sheepCount = random.nextInt(61);
                int wolfCount = random.nextInt(21);

                for (int grass = 0; grass < grassCount; grass++) {
                    field[i][j].addGrass(new Grass());
                }
                for (int sheep = 0; sheep < sheepCount; sheep++) {
                    field[i][j].addAnimal(new Sheep());
                }
                for (int wolf = 0; wolf < wolfCount; wolf++) {
                    field[i][j].addAnimal(new Wolf());
                }
            }
        }
    }
}
