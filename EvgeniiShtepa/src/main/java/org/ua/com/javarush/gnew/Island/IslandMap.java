package org.ua.com.javarush.gnew.Island;

import lombok.Getter;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class IslandMap {
    private static IslandMap INSTANCE;
    private final int width = 10;
    private final int height = 10;
    @Getter
    private final Cell[][] cells = new Cell[width][height]; //TODO: добавить XML для параметров острова

    private IslandMap() {
    }

    public static IslandMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IslandMap();
        }
        return INSTANCE;
    }


    public void initIsland(List<Class<? extends Organism>> animalClasses) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = createPopulatedCell(animalClasses);
            }
        }
    }

    private Cell createPopulatedCell(List<Class<? extends Organism>> animalClasses) {
        Cell cell = new Cell();
        for (Class<? extends Organism> animalClass : animalClasses) {
            try {
                Organism organism = animalClass.getDeclaredConstructor().newInstance();

                int count = ThreadLocalRandom.current().nextInt(1, organism.getMaxCountPerCell() + 1);

                for (int i = 0; i < count; i++) {
                    Organism newAnimal = animalClass.getDeclaredConstructor().newInstance();
                    cell.addAnimal(newAnimal);
                }

            } catch (Exception e) {
                e.printStackTrace(); //TODO: добавить эксепшн
            }
        }
        return cell;
    }
}
