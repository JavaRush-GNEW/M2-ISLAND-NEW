package game.entity.island;

import game.entity.animal.Animal;
import game.entity.plant.Plant;

import java.util.HashSet;
import java.util.Set;

public class Area {
    private int coordinateX;
    private int coordinateY;

    Set<Animal> animalSet = new HashSet<Animal>();
    Set<Plant> plantSet = new HashSet<Plant>();

    public Set<Animal> getAnimalSet() {
        return animalSet;
    }

    public Set<Plant> getPlantSet() {
        return plantSet;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }
}
