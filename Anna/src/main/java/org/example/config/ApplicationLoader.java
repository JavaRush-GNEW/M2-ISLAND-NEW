package org.example.config;

import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.Organism;

import java.util.HashMap;
import java.util.Set;

public class ApplicationLoader {
    private static ApplicationLoader INSTANCE;

    private ApplicationLoader() {
    }

    public static ApplicationLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationLoader();
        }
        return INSTANCE;
    }

    ApplicationContext applicationContext = ApplicationContext.getInstance();

    public ApplicationContext init() {
        initGameField(5, 5);
        return applicationContext;
    }

    public void initGameField(int width, int height) {
        GameField gameField = new GameField(width, height);
        Cell[][] cells = gameField.getCells();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                HashMap<Class<? extends Organism>, Set<Organism>> residents = new HashMap<>();
                cells[i][j] = new Cell(residents);
            }
        }
        this.applicationContext.setGameField(gameField);
    }
}
