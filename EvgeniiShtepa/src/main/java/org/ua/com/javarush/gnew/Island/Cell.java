package org.ua.com.javarush.gnew.Island;

import lombok.Getter;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Cell {


    private final Map<Class<? extends Organism>, List<Organism>> residents;

    public Cell() {
        this.residents = new HashMap<>();
    }

    public void addAnimal(Organism organism) {
        residents.computeIfAbsent(organism.getClass(), k -> new ArrayList<>()).add(organism);
    }
}

