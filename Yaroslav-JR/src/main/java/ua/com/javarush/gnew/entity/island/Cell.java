package ua.com.javarush.gnew.entity.island;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.awt.AWTEventMulticaster.remove;

public class Cell {

    private final ConcurrentHashMap<Class<? extends Organism>, Set<Organism>> residents;


    public Cell(ConcurrentHashMap<Class<? extends Organism>, Set<Organism>> residents) {
        this.residents = residents;
    }

    public Map<Class<? extends Organism>, Set<Organism>> getResidents() {
        return residents;
    }
    public synchronized boolean add(Organism organism){
        Class<? extends Organism> organismClass = organism.getClass();
        residents.putIfAbsent(organismClass, ConcurrentHashMap.newKeySet());
        return residents.get(organismClass).add(organism);
    }
}
