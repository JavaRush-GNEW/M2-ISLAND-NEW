package game.entity.island;

import game.entity.animal.Animal;
import game.entity.plant.Plant;

import java.util.Set;

public class Area {
    private int coordinateX;
    private int coordinateY;

    Set<Animal> animalSet;
    Set<Plant> plantSet;

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
