package org.example.config;

import org.example.lifecycle.LifeCycleManager;
import org.example.lifecycle.PlantLifeCycleManager;
import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.Organism;
import org.example.model.organism.animal.Animal;
import org.example.statistics.StatisticsCollector;

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
        GameField gameField = applicationContext.getGameField();
        LifeCycleManager lifeCycleManager = new LifeCycleManager(gameField);
        PlantLifeCycleManager plantLifeCycleManager = new PlantLifeCycleManager(applicationContext);
        StatisticsCollector statisticsCollector = new StatisticsCollector(gameField);

        lifeCycleManager.startLifeCycle();
        plantLifeCycleManager.startPlantLifeCycle();
        statisticsCollector.startCollecting();
        while (true) {
            if (areAllAnimalsDead(gameField)) {
                System.out.printf("All animals are dead. Shutting down the simulation...");
                lifeCycleManager.stopLifeCycle();
                statisticsCollector.stopCollecting();
                statisticsCollector.stopCollecting();
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Application interrupted");
                break;
            }
        }


        return applicationContext;
    }

    private boolean areAllAnimalsDead(GameField gameField) {
        Cell[][] cells = gameField.getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                for (Set<Organism> organisms : cell.getResidents().values()) {
                    for (Organism organism : organisms) {
                        if (organism instanceof Animal && ((Animal<?>) organism).isAlive()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void initGameField(int width, int height) {
        GameField gameField = new GameField(width, height);
        Cell[][] cells = gameField.getCells();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j, new HashMap<>());
                cells[i][j] = cell;
                HashMap<Class<? extends Organism>, Set<Organism>> residents = OrganismFactory.createResidents(cell);
                cell.setResidents(residents);
            }
        }
        this.applicationContext.setGameField(gameField);
    }

}
