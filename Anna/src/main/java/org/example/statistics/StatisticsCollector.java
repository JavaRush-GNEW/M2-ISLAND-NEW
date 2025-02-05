package org.example.statistics;

import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.Organism;
import org.example.model.organism.animal.Animal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StatisticsCollector {
    private final GameField gameField;
    private final ScheduledExecutorService scheduler;

    public StatisticsCollector(GameField gameField) {
        this.gameField = gameField;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startCollecting() {
        scheduler.scheduleAtFixedRate(this::collectAndPrintStatistics, 0, 5, TimeUnit.SECONDS);
    }

    public void stopCollecting() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void collectAndPrintStatistics() {
        System.out.println("=== Statistics ===");
        Cell[][] cells = gameField.getCells();
        Map<String, Integer> organismCount = new HashMap<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                Map<Class<? extends Organism>, Set<Organism>> residents = cell.getResidents();
                System.out.printf("Cell (%d, %d): \n", i, j);
                for (Map.Entry<Class<? extends Organism>, Set<Organism>> entry : residents.entrySet()) {
                    String organismName = entry.getKey().getSimpleName();
                    if (organismName.equals("Plant")) {
                        break;
                    }
                    long aliveCount = entry.getValue().stream()
                            .filter(organism -> organism instanceof Animal && ((Animal<?>) organism).isAlive())
                            .count();

                    organismCount.merge(organismName, (int) aliveCount, Integer::sum);
                    organismCount.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
                }

            }
        }
    }
}
