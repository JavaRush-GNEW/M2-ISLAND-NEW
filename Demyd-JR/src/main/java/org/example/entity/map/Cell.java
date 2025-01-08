package org.example.entity.map;



import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.animal.interfaces.Reproduction;
import org.example.entity.animal.predator.Predator;
import org.example.entity.plant.Plant;


import java.util.ArrayList;
import java.util.List;


public class Cell {
    private List<Animal> entities = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();

    public boolean addEntity(Animal animal) {
        // Count animal per cell //
        long currentCount = entities.stream()
                .filter(entity -> entity.getClass() == animal.getClass())
                .count();
        // Our test //
        if (currentCount < animal.getMaxPerCellAnimal()) {
            entities.add(animal);
            return true;
        } else {
            entities.remove(animal);
//            System.out.println("Не можна додати " + animal.getClass().getSimpleName() + " у клітинку: досягнуто максимум.");
            return false;
        }
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
