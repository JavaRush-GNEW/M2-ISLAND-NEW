package map;


import context.ApplicationContext;
import entities.Animal;
import entities.herbivores.Rabbit;
import entities.predators.Wolf;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.*;

public class IslandMap implements GameMap{
    private final Location[][] grid;
    private ExecutorService executor;
    private boolean isRunning;
    private ThreadLocalRandom random = ThreadLocalRandom.current();
    public static final ScheduledExecutorService plantScheduler = Executors.newScheduledThreadPool(1);
    private ApplicationContext applicationContext;

    private int rows;
    private int cols;

    public IslandMap(int rows, int cols,ApplicationContext applicationContext) {
        this.grid = new Location[rows][cols];
        this.executor = Executors.newFixedThreadPool(rows*cols);
        this.isRunning = true;
        this.rows = rows;
        this.cols = cols;
        this.applicationContext = applicationContext;
    }


    private void init(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Location(i, j,applicationContext);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].initNeighborsLocations();
            }
        }

        executor = Executors.newFixedThreadPool(applicationContext.getTHREADS_LIMIT());
        populateIsland();
    }


    private void populateIsland()  {
        HashMap<Class<? extends Animal>, Integer> animalsPerCeil = applicationContext.getAnimalsPerCeil();
        Set<Map.Entry<Class<? extends Animal>, Integer>> entries = animalsPerCeil.entrySet();
        for (Location[] row : grid) {
            for (Location location : row) {
                for (Map.Entry<Class<? extends Animal>, Integer> animal : entries) {
                    int animalCount = random.nextInt(animal.getValue()+1);
                    for (int i = 0; i <animalCount ; i++) {
                        Class<? extends Animal> clazz = animal.getKey();
                        Animal animalInstance = createAnimal(location, clazz);
                        location.addAnimal(animalInstance);
                    }
                }
            }
        }
        System.out.println("Island populated with random animals.");
    }

    private Animal createAnimal(Location location, Class<? extends Animal> clazz) {
        Animal instance = null;
        try {
            instance = clazz.getDeclaredConstructor(Location.class).newInstance(location);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public void start()  {
        init();
        System.out.println("Starting simulation...");
        while (isRunning) {

            for (Location[] locations : grid) {
                for (Location location : locations) {
                    executor.submit(location);
                }
            }
            try {
                Thread.sleep(2000);
                printStatistics();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isSimulationOver()) {
                isRunning = false;
                System.out.println("Simulation over!");
                shutdown();
                break;
            }
        }
    }

    public boolean isSimulationOver() {
        int predators = 0;
        int herbivores = 0;
        for (Location[] row: grid){
            for (Location location: row){
                predators+=location.countPredators();
                herbivores+= location.countHerbivores();
            }
        }
        return predators == 0 || herbivores == 0;
    }


    public void printStatistics() {
        // Карта для зберігання кількості кожного типу тварин
        Map<Class<? extends Animal>, Integer> animalCounts = new HashMap<>();
        int predators = 0;
        int herbivores = 0;
        // Обхід усіх локацій
        for (Location[] row : grid) {
            for (Location location : row) {
                synchronized (location) {
                    // Підрахунок тварин
                    for (Map.Entry<Class<? extends Animal>, Set<Animal>> entry : location.getResidents().entrySet()) {
                        animalCounts.merge(entry.getKey(), entry.getValue().size(), Integer::sum);
                        predators+=location.countPredators();
                        herbivores+=location.countHerbivores();
                    }
                }
            }
        }

        System.out.println("\n====== Current Game Statistics ======");

        for (Map.Entry<Class<? extends Animal>, Integer> entry : animalCounts.entrySet()) {
            System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
        }
        System.out.println("Predators: " + predators);
        System.out.println("Herbivores: " + herbivores);
        System.out.println("=====================================\n");
    }


    public void shutdown() {
        System.out.println("Shutting down the simulation...");

        // Зупинка планувальника для рослин
        if (!plantScheduler.isShutdown()) {
            plantScheduler.shutdown();
            try {
                if (!plantScheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                    plantScheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                plantScheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n====== Final Game Statistics ======");
        printStatistics();
        System.out.println("Simulation ended.");
    }


    @Override
    public boolean isValidCell(int row, int coll) {
        return row >= 0 && row < grid.length && coll >= 0 && coll < grid[0].length;
    }

    public Location getLocation(int row, int col) {
        return grid[row][col];
    }
}

