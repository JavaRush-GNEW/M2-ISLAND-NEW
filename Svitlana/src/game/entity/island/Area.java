package game.entity.island;

import game.entity.animal.Animal;
import game.entity.plant.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Area {
    private int coordinateX;
    private int coordinateY;

    //private Set<Animal> animalSet = new HashSet<Animal>();
    private Map<String, Set<Animal>> animalMap = new HashMap<>();
    //private Set<Plant> plantSet = new HashSet<Plant>();
    private Map<String, Set<Plant>> plantMap = new HashMap<>();

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

//    public Set<Animal> getAnimalSet() {
//        return animalSet;
//    }
//
//    public Set<Plant> getPlantSet() {
//        return plantSet;
//    }

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
