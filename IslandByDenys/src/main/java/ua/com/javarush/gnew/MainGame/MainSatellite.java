package ua.com.javarush.gnew.MainGame;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Plant.Plant;

import java.util.Random;

public interface MainSatellite {
     static void placeAnimalRandomly(Field field, Animal animal, Random random) {
        int rows = field.getWidth();
        int cols = field.getHeight();
        while (true) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            Cell cell = field.getCell(x, y);
            if (cell.getAnimal() == null) {
                cell.setAnimal(animal);
                break;
            }
        }
    }


     static void placePlantRandomly(Field field, Plant plant, Random random) {
        int rows = field.getWidth();
        int cols = field.getHeight();
        while (true) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            Cell cell = field.getCell(x, y);
            if (cell.getPlant() == null) {
                cell.setPlant(plant);
                break;
            }
        }
    }
}
