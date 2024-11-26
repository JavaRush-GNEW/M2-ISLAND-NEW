package ua.com.javarush.gnew.entity.island;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.*;

import static java.awt.AWTEventMulticaster.remove;

public class Cell {

    private final Map<Class<? extends Organism>, Set<Organism>> residents;


    public Cell(Map<Class<? extends Organism>, Set<Organism>> residents) {
        this.residents = residents;
    }

    public Map<Class<? extends Organism>, Set<Organism>> getResidents() {
        return residents;
    }
    public boolean add(Organism organism){
        Class<? extends Organism> organismClass = organism.getClass();
        residents.putIfAbsent(organismClass, new HashSet<>());
        return residents.get(organismClass).add(organism);
    }
}
