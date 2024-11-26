package org.example.entity.map;


import org.example.entity.LivingEntity;
import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.animal.predator.Predator;
import org.example.entity.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {
    private List<Herbivore> herbivores;
    private List<Predator> predators;
    private List<Plant> plants;
    private List<LivingEntity> entities;

    public Cell() {
        herbivores = new ArrayList<>();
        predators = new ArrayList<>();
        plants = new ArrayList<>();
        entities = new ArrayList<>();

    }

                      // HERBIVORE
    public List<Herbivore> getHerbivores() {
        return entities.stream()
                .filter(e -> e instanceof Herbivore)
                .map(e -> (Herbivore) e)
                .collect(Collectors.toList());
    }
                    // PREDATOR
    public List<Predator> getPredators() {
        return entities.stream()
                .filter(e -> e instanceof Predator)
                .map(e -> (Predator) e)
                .collect(Collectors.toList());
    }

                    // PLANT
    public List<Plant> getPlants() {
        return entities.stream()
                .filter(e -> e instanceof Plant)
                .map(e -> (Plant) e)
                .collect(Collectors.toList());
    }

            //LivingEntities
    public List<LivingEntity> getEntities() {
        return entities;
    }
    public void addEntity(LivingEntity entity) {
        entities.add(entity);
    }


    // STATS Method
    public void printCellInfo(){
        System.out.println("Cell info : ");
        System.out.println("Herbivores : " + herbivores.size());
        System.out.println("Predators : " + predators.size());
        System.out.println("Plants : " + plants);
    }
}
