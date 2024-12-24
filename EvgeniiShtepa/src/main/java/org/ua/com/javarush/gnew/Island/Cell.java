package org.ua.com.javarush.gnew.Island;

import lombok.Getter;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Cell {
    int x;
    int y;
    private Map<Class<? extends Organism>, List<Organism>> residents;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.residents = new HashMap<>();
    }

    public void addAnimal(Organism organism) {
        residents.computeIfAbsent(organism.getClass(), k -> new ArrayList<>()).add(organism);
    }

    public void removeAnimal(Organism organism) {
        List<Organism> animals = residents.get(organism.getClass());
        animals.remove(0);
    }

    public List<Organism> getAnimalByType(Organism organism) {
        Class<? extends Organism> animalClass = organism.getClass();
        return residents.get(animalClass);
    }


}

