package org.ua.com.javarush.gnew;

import org.ua.com.javarush.gnew.Island.IslandManager;
import org.ua.com.javarush.gnew.Island.IslandMap;
import org.ua.com.javarush.gnew.config.ThreadManager;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import org.ua.com.javarush.gnew.model.Animals.Predator.Bear;
import org.ua.com.javarush.gnew.model.Animals.Predator.Wolf;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        IslandMap islandMap = IslandMap.getInstance();
        IslandManager islandManager = IslandManager.getInstance();
        List<Class<? extends Organism>> animals = List.of(Wolf.class, Horse.class, Bear.class);
        islandMap.initIsland(animals);
        ThreadManager threadManager = new ThreadManager(4, islandMap.getHeight());
        threadManager.startSimulation();



    }
}
