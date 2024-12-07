package org.ua.com.javarush.gnew.Island;

import lombok.Getter;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    @Getter
    List<Organism> residents;

    public Cell() {
        this.residents = new ArrayList<>();
    }


}
