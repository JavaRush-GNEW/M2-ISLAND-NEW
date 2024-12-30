package org.ua.com.javarush.gnew.Threads;


import org.ua.com.javarush.gnew.Config.AnimalCollection;
import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandManager;
import org.ua.com.javarush.gnew.Island.IslandMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    private final IslandMap islandMap;
    private final IslandManager islandManager;
    private final ScheduledExecutorService executorServiceForSimulatuin;
    private final ScheduledExecutorService executorForStatistics;

    private static ThreadManager INSTANCE;
    private final int islandHeight;
    private final int threadNum;

    private ThreadManager() {
        this.threadNum = 4;
        this.executorServiceForSimulatuin = Executors.newScheduledThreadPool(threadNum);
        this.islandMap = IslandMap.getInstance();
        this.islandManager = IslandManager.getInstance();
        this.islandHeight = islandMap.getHeight();
        this.executorForStatistics = Executors.newScheduledThreadPool(1);
    }

    public static ThreadManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadManager();
        }
        return INSTANCE;
    }


    public void startGame() {
        islandMap.initIsland(AnimalCollection.getInstane().getAnimals());
        Cell[][] cells = islandMap.getCells();
        islandManager.growGrassInCells();
        int cellsPerThread = (int) Math.ceil((double) islandHeight / threadNum);

        for (int i = 0; i < threadNum; i++) {
            int threadIndex = i;

            executorServiceForSimulatuin.scheduleAtFixedRate(() -> {
                int start = cellsPerThread * threadIndex;
                int end = Math.min(start + cellsPerThread, islandHeight);
                for (int j = start; j < end; j++) {
                    Cell[] row = cells[j];
                    for (Cell currentCell: row) {
                        islandManager.processCell(currentCell);
                    }
                }
            }, 0, 5, TimeUnit.SECONDS);
        }
    }

    public void startCollectStatistics() {
        executorForStatistics.scheduleAtFixedRate(() -> {
            islandManager.collectStatistics();
            islandManager.printStatistics();
        }, 0, 5, TimeUnit.SECONDS);
    }


}
