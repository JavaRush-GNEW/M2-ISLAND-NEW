package org.ua.com.javarush.gnew.Island;

import lombok.Getter;
import lombok.Setter;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Cell {
    private final int x;
    private final int y;
    @Setter
    private int grassAmount;
    private Map<Class<? extends Organism>, List<Organism>> residents;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.residents = new HashMap<>();
        this.grassAmount = 20;
    }

    public void addAnimal(Organism organism) {
        residents.computeIfAbsent(organism.getClass(), k -> new ArrayList<>()).add(organism);
    }

    public void removeAnimal(Organism organism) {
        List<Organism> animals = residents.get(organism.getClass());
        animals.remove(organism);
    }
}

