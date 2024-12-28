package org.ua.com.javarush.gnew.config;


import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandManager;
import org.ua.com.javarush.gnew.Island.IslandMap;
import org.ua.com.javarush.gnew.Main;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;


import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    private IslandMap islandMap;
    private IslandManager islandManager;
    private ScheduledExecutorService executorService;
    private static ThreadManager INSTANCE;
    private int islandHeight;
    private int threadNum;

    private ThreadManager() {
        this.threadNum = 4;
        this.executorService = Executors.newScheduledThreadPool(threadNum);
        this.islandMap = IslandMap.getInstance();
        this.islandManager = IslandManager.getInstance();
        this.islandHeight = islandMap.getHeight();
    }

    public static ThreadManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadManager();
        }
        return INSTANCE;
    }


    public void startGame(List<Class<? extends Organism>> animals) {
        islandMap.initIsland(animals);
        Cell[][] cells = islandMap.getCells();
        islandManager.growGrassInCells();
        int cellsPerThread = (int) Math.ceil((double) islandHeight / threadNum);

        for (int i = 0; i < threadNum; i++) {
            int threadIndex = i;

            executorService.scheduleAtFixedRate(() -> {
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
}
