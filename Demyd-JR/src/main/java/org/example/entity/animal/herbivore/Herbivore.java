package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public abstract class Herbivore extends Animal {
    private String name;

    public Herbivore(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
