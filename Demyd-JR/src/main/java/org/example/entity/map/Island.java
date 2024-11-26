package org.example.entity.map;


import org.example.entity.animal.herbivore.Horse;
import org.example.entity.animal.predator.Wolf;
import org.example.entity.plant.Cactus;

import java.util.Random;

public class Island {
    private final Cell[][] grid;
    private final int width;
    private final int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        initializeIsland();
    }

    private void initializeIsland() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Cell();


                int numberOfEntities = random.nextInt(5); // 0–4 мешканців у клітинці
                for (int k = 0; k < numberOfEntities; k++) {
                    int entityType = random.nextInt(3);
                    switch (entityType) {
                        case 0 -> grid[i][j].addEntity(new Horse());
                        case 1 -> grid[i][j].addEntity(new Wolf());
                        case 2 -> grid[i][j].addEntity(new Cactus());
                    }
                }
            }
        }
    }

    public void displayIsland() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print("[" + grid[i][j].getEntities().size() + "] ");
            }
            System.out.println();
        }
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }


}
