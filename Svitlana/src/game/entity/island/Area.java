package game.entity.island;

import game.entity.animal.Animal;
import game.entity.plant.Plant;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Area {
    private int coordinateX;
    private int coordinateY;

    private Map<String, Set<Animal>> animalMap = new ConcurrentHashMap<>();
    private Map<String, Set<Plant>> plantMap = new ConcurrentHashMap<>();

    public Area(int j, int i) {
        this.coordinateX = j;
        this.coordinateY = i;
    }

    public Map<String, Set<Animal>> getAnimalMap() {
        return animalMap;
    }

    public Map<String, Set<Plant>> getPlantMap() {
        return plantMap;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getAreaAnimalsQuantity(String animalName) {
        if (animalMap.containsKey(animalName)) {
            Set<Animal> animals = animalMap.get(animalName);
            return animals.size();
        } else return 0;
    }

    public int printAreaPlants(String plantName) {
        if (animalMap.containsKey(plantName)) {
            Set<Plant> plants = plantMap.get(plantName);
            return plants.size();
        } else return 0;
    }

    @Override
    public String toString() {
        return "Area [coordinateX=" + coordinateX + ", coordinateY=" + coordinateY + "]";
    }
}
