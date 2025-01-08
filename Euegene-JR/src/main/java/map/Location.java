package map;

import context.ApplicationContext;
import entities.*;
import entities.herbivores.Herbivore;
import entities.plants.Plant;
import entities.predators.Predator;
import entities.Animal;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static map.IslandMap.plantScheduler;

public class Location implements Runnable {
    private final int row;
    private final int col;
    private HashMap<Class<? extends Animal>,Set<Animal>> residents;
    private Set<Plant> plants;
    List<int[]> validDirections = new ArrayList<>();
    private static final int MAX_PLANTS = 100;
    private ApplicationContext applicationContext;


    public Location(int row, int col,ApplicationContext applicationContext) {
        this.row = row;
        this.col = col;
        this.residents = new HashMap<>();
        this.plants = new HashSet<>();
        this.applicationContext = applicationContext;
        startPlantGrowth();
    }

    public synchronized HashMap<Class<? extends Animal>,Set<Animal>> getResidents() {
        return residents;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public synchronized void addAnimal(Animal animal) {
        Class<? extends Animal> animalClass = animal.getClass();
        residents.putIfAbsent(animalClass,new HashSet<>());
        residents.get(animalClass).add(animal);
    }

    public synchronized int countAllAnimals() {
        return residents.values().stream()
                .mapToInt(Set::size)
                .sum();
    }

    public synchronized int countPredators() {
        return residents.entrySet().stream()
                .filter(entry -> Predator.class.isAssignableFrom(entry.getKey()))
                .mapToInt(entry -> entry.getValue().size())
                .sum();
    }

    public synchronized int countHerbivores() {
        return residents.entrySet().stream()
                .filter(entry -> Herbivore.class.isAssignableFrom(entry.getKey()))
                .mapToInt(entry -> entry.getValue().size())
                .sum();
    }


    @Override
    public void run() {
        Set<Map.Entry<Class<? extends Animal>, Set<Animal>>> entriesCopy;

        synchronized (residents) {
            entriesCopy = new HashSet<>(residents.entrySet());
        }

        for (Map.Entry<Class<? extends Animal>, Set<Animal>> entry : entriesCopy) {
            Class<? extends Animal> animalClass = entry.getKey();
            Set<Animal> animalSet = entry.getValue();

            if (animalSet == null) continue;

            Iterator<Animal> iterator = new HashSet<>(animalSet).iterator();
            while (iterator.hasNext()) {
                Animal animal = iterator.next();
                synchronized (animal) {
                    if (!animal.isAlive()) {
                        synchronized (residents) {
                            residents.get(animalClass).remove(animal);
                        }
                        continue;
                    }
                    animal.performAction();
                }
            }
        }
    }




    private void startPlantGrowth() {
        plantScheduler.scheduleAtFixedRate(() -> {
            if (plants.size() < MAX_PLANTS) {
                int plantsToGrow = ThreadLocalRandom.current().nextInt(1, 6);
                for (int i = 0; i < plantsToGrow; i++) {
                    if (plants.size() < MAX_PLANTS) {
                        plants.add(new Plant());
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    public void initNeighborsLocations(){
        int currentRow = this.getRow();
        int currentCol = this.getCol();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = currentRow + dir[0];
            int newCol = currentCol + dir[1];
            if (applicationContext.getGAME_MAP().isValidCell(newRow, newCol)) {
                validDirections.add(new int[]{newRow, newCol});
            }
        }
    }

    public Location getValidLocation(){
        Random random = new Random();
        Location location = this;
        if(!validDirections.isEmpty()){
            int[] direction = validDirections.get(random.nextInt(validDirections.size()));
            int newRow = direction[0];
            int newCol = direction[1];
            location = applicationContext.getGAME_MAP().getLocation(newRow, newCol);
        }

        return location;
    }

    public boolean hasSpace(){
        return residents.values().stream().mapToInt(Set::size).sum() < 300;
    }

    public synchronized void removeAnimal(Animal animal) {
        Set<Animal> organismsTypeSet = residents.get(animal.getClass());
        if (organismsTypeSet != null) {
            Iterator<Animal> iterator = organismsTypeSet.iterator();
            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.equals(animal)) {
                    iterator.remove(); // Видалення за допомогою ітератора
                    break;
                }
            }
        }
    }


    public synchronized Set<Plant> getPlants() {
        return plants;
    }
}



