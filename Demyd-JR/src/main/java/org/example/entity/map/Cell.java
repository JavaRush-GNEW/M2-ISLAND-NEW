package org.example.entity.map;


import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.animal.predator.Predator;
import org.example.entity.plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Herbivore> herbivores;
    private List<Predator> predators;
    private List<Plant> plants;

    public Cell() {
        herbivores = new ArrayList<>();
        predators = new ArrayList<>();
        plants = new ArrayList<>();
    }

                      // HERBIVORE
    public List<Herbivore> getHerbivores() {
        return herbivores;
    }
                    // PREDATOR
    public List<Predator> getPredators() {
        return predators;
    }
                    // PLANT
    public List<Plant> getPlants() {
        return plants;
    }
                // STATS Method
    public void printCellInfo(){
        System.out.println("Cell info : ");
        System.out.println("Herbivores : " + herbivores);
        System.out.println("Predators : " + predators);
        System.out.println("Plants : " + plants);
    }
}
