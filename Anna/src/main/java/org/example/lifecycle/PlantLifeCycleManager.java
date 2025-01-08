package org.example.lifecycle;

import org.example.config.ApplicationContext;
import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.Organism;
import org.example.model.organism.plant.Plant;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlantLifeCycleManager {
    private static final int PERIODICITY_SECONDS = 5;

    private ScheduledExecutorService scheduled;
    private ApplicationContext applicationContext;

    public PlantLifeCycleManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.scheduled = Executors.newScheduledThreadPool(1);
    }

    public void startPlantLifeCycle() {
        scheduled.scheduleAtFixedRate(this::invokePlantLifeCycle, 0, PERIODICITY_SECONDS, TimeUnit.SECONDS);
    }

    private void invokePlantLifeCycle() {
        GameField gameField = applicationContext.getGameField();
        Cell[][] cells = gameField.getCells();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Set<Organism> plants = new HashSet<>(cell.getResidents().get(Plant.class));
                for (Organism organism : plants) {
                    if (organism instanceof Plant) {
                        ((Plant) organism).lifeCycle();
                    }
                }
            }
        }
    }

    public void stop() {
        if (scheduled != null && !scheduled.isShutdown()) {
            scheduled.isShutdown();
        }
    }
}
