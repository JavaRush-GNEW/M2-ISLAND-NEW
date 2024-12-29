package org.ua.com.javarush.gnew;


import org.ua.com.javarush.gnew.config.ThreadManager;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import org.ua.com.javarush.gnew.model.Animals.Predator.Wolf;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Class<? extends Organism>> animals = List.of(Wolf.class, Horse.class);

        ThreadManager threadManager = ThreadManager.getINSTANCE();
        threadManager.startGame(animals);
        threadManager.startCollectStatistics();


    }
}
