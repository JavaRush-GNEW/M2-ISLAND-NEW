package org.ua.com.javarush.gnew.Island;

import lombok.Getter;
import org.ua.com.javarush.gnew.Config.AppConfig;
import org.ua.com.javarush.gnew.Config.ConfigLoader;
import org.ua.com.javarush.gnew.exeptions.PopulateCellException;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class IslandMap {
    private static IslandMap INSTANCE;
    @Getter
    private final int width;
    @Getter
    private final int height;
    @Getter
    private final Cell[][] cells;

    private IslandMap() {
        AppConfig.IslandConfig config = ConfigLoader.getConfig().getIsland();
        this.width = config.getWidth();
        this.height = config.getHeight();
        cells = new Cell[height][width];
    }

    public static IslandMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IslandMap();
        }
        return INSTANCE;
    }


    public void initIsland(List<Class<? extends Organism>> animalClasses) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = createPopulatedCell(animalClasses, x, y);
            }
        }
    }

    private Cell createPopulatedCell(List<Class<? extends Organism>> animalClasses, int x, int y) {
        Cell cell = new Cell(x, y);
        for (Class<? extends Organism> animalClass : animalClasses) {
            try {
                Organism organism = animalClass.getDeclaredConstructor().newInstance();
                int animalCountPerCell = ThreadLocalRandom.current().nextInt(1, organism.getMAX_CELL_COUNT() + 1);
                for (int i = 0; i < animalCountPerCell; i++) {
                    Organism newAnimal = animalClass.getDeclaredConstructor().newInstance();
                    cell.addAnimal(newAnimal);
                }

            } catch (Exception e) {
                throw new PopulateCellException("Ошибка создания клетки с животными", e);
            }
        }
        return cell;
    }
}
