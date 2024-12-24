package org.ua.com.javarush.gnew;

import org.ua.com.javarush.gnew.Island.IslandManager;
import org.ua.com.javarush.gnew.Island.IslandMap;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import org.ua.com.javarush.gnew.model.Animals.Predator.Wolf;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        IslandMap islandMap = IslandMap.getInstance();
        List<Class<? extends Organism>> animals = List.of(Wolf.class, Horse.class);
        islandMap.initIsland(animals);
        IslandManager islandManager = new IslandManager();
        islandManager.startSimulation();

    }
}
