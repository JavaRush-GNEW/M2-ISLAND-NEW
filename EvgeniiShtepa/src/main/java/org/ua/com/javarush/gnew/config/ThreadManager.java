package org.ua.com.javarush.gnew.config;

import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandManager;
import org.ua.com.javarush.gnew.Island.IslandMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadManager {
    private final IslandManager islandManager = IslandManager.getInstance();
    private final IslandMap islandMap = IslandMap.getInstance();
    private final ScheduledExecutorService scheduler;
    private final ExecutorService executor;

    private final int numThreads;
    private final int islandHeight;

    public ThreadManager(int numThreads, int islandHeight) {
        this.numThreads = numThreads;
        this.islandHeight = islandHeight;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public void startSimulation(){
        scheduler.scheduleAtFixedRate(this::simulateTick, 0, 5, TimeUnit.SECONDS);

    }

    private void simulateTick(){

        islandManager.growGrassInCells();
        int rowsPerThread = (int) Math.ceil((double) islandHeight / numThreads);
        List<Callable<Void>> tasks = new ArrayList<>();

        for(int threadIndex = 0; threadIndex < numThreads; threadIndex++){
            final int startRow = threadIndex * rowsPerThread;
            final int endRow = Math.min(startRow + rowsPerThread, islandHeight);

            tasks.add(() -> {
                processPartOfIsland(startRow, endRow);
                return null;
            });
        }


        try {
            List<Future<Void>> futures = executor.invokeAll(tasks);
            for (Future<Void> f : futures){
                f.get(); //
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        islandManager.collectStatistics();
        islandManager.printStatistic();
    }

    private void processPartOfIsland(int startRow, int endRow) {
        Cell[][] cells = islandMap.getCells();
        int width = islandMap.getWidth();

        for(int y = startRow; y < endRow; y++){
            for(int x = 0; x < width; x++){
                Cell currentCell = cells[y][x];
                islandManager.processCell(currentCell);
            }
        }
    }

    public void shutdown(){
        System.out.println("Завершаем работу ThreadManager...");
        scheduler.shutdown();
        executor.shutdown();
        try {
            if(!scheduler.awaitTermination(5, TimeUnit.SECONDS)){
                scheduler.shutdownNow();
            }
            if(!executor.awaitTermination(5, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("ThreadManager завершён.");
    }
}
