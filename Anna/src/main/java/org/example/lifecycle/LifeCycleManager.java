package org.example.lifecycle;

import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.animal.Animal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LifeCycleManager {
    private final GameField gameField;
    private final ExecutorService executorService;

    public LifeCycleManager(GameField gameField) {
        this.gameField = gameField;
        this.executorService = Executors.newCachedThreadPool();
    }

    public void startLifeCycle() {
        Runnable task = () -> {
            while (true) {
                Cell[][] cells = gameField.getCells();
                for (Cell[] row : cells) {
                    for (Cell cell : row) {
                        processCell(cell);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("LifeCycleManager interrupted");
                    break;
                }
            }
        };
        executorService.submit(task);
    }

    private void processCell(Cell cell) {
        cell.getResidents().values().forEach(organisms -> {
            organisms.stream().
                    filter(organism -> organism instanceof Animal && ((Animal<?>) organism).isAlive())
                    .forEach(organism -> executorService.submit(organism::lifeCycle));
        });
    }

    public void stopLifeCycle() {
        executorService.shutdownNow();
    }
}
