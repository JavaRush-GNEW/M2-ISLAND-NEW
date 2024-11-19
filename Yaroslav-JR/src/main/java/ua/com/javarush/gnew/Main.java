package ua.com.javarush.gnew;

import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Island island = new Island();


        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < 1000; j++){

                Cell cell = island.getField()[i][j];

                int wolfCount = random.nextInt(21);
                int sheepCount = random.nextInt(61);
                int grassCount = random.nextInt(101);


                for (int k = 0; k < wolfCount; k++){
                    cell.addAnimal(new Wolf());
                }
                for (int g = 0; g < sheepCount; g++){
                    cell.addAnimal(new Sheep());
                }
                for (int h = 0; h < grassCount; h++){
                    cell.addGrass(new Grass());
                }
            }
        }

    }
}