package org.example.config;

import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.Organism;

import java.util.HashMap;
import java.util.Set;

public class ApplicationLoader {
    private static final int GAME_FIELD_WIDTH = 5;
    private static final int GAME_FIELD_HEIGHT = 5;
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
        initGameField(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        return applicationContext;
    }

    public void initGameField(int width, int height) {
        GameField gameField = new GameField(width, height);
        Cell[][] cells = gameField.getCells();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                HashMap<Class<? extends Organism>, Set<Organism>> residents = OrganismFactory.createResidents();
                cells[i][j] = new Cell(residents);
            }
        }
        this.applicationContext.setGameField(gameField);
    }

}
