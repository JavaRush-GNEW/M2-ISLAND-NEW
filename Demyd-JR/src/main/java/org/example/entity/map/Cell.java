package org.example.entity.map;



import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.animal.predator.Predator;


import java.util.ArrayList;
import java.util.List;


public class Cell {
    private List<Animal> entities = new ArrayList<>();

    public void addEntity(Animal entity) {
        entities.add(entity);
    }
    public List<Animal> getEntities(){
        return entities;
    }

    public void removeEntity(Animal animal) {
        entities.remove(animal); // Видалити тваринку зі списку
    }
    @Override
    public String toString() {
        long herbivoresCount = entities.stream().filter(e -> e instanceof Herbivore).count();
        long predatorsCount = entities.stream().filter(e -> e instanceof Predator).count();
        long plantsCount = entities.stream().filter(e -> false).count();

        return "Cell{" +
                "H=" + herbivoresCount +
                ", W=" + predatorsCount +
                '}';
    }
}
