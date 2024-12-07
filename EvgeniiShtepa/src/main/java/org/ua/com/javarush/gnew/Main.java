package org.ua.com.javarush.gnew;

import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.Map;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import org.ua.com.javarush.gnew.model.Animals.Predator.Wolf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Map map = new Map(5, 5);


        List<Organism> animals = new ArrayList<>();
        animals.add(new Wolf());
        animals.add(new Horse());

        map.mapInit(animals);

        



    }
}
