package org.example.entity.map;



import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.animal.predator.Predator;
import org.example.entity.plant.Plant;


import java.util.ArrayList;
import java.util.List;


public class Cell {
    private List<Animal> entities = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();

    public void addEntity(Animal entity) {
        entities.add(entity);
    }
    public List<Animal> getEntities(){
        return entities;
    }
    public void addPlant(Plant plant) {
        plants.add(plant);
    }
    public List<Plant> getPlants(){
        return plants;
    }


    public void removeEntity(Animal animal) {
        entities.remove(animal);
    }
    @Override
    public String toString() {
        long herbivoresCount = entities.stream().filter(e -> e instanceof Herbivore).count();
        long predatorsCount = entities.stream().filter(e -> e instanceof Predator).count();
        long plantsCount = plants.stream().filter(e -> e instanceof Plant).count();

        return "Cell{" +
                "H=" + herbivoresCount +
                ", P=" + predatorsCount +
                ", Pl=" + plantsCount +
                '}';
    }

}
