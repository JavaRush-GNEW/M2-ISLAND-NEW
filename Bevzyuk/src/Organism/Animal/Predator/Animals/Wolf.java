package Organism.Animal.Predator.Animals;

import Organism.Animal.Predator.Predator;

import java.util.HashMap;

public class Wolf extends Predator {
    public Wolf() {
        super("Вовк", 50, 8, 3, 30);
        this.preyChances = new HashMap<>() {{
            put("Horse", 10.0);
            put("Deer", 15.0);
            put("Rabbit", 60.0);
            put("Mouse", 80.0);
            put("Goat", 60.0);
            put("Sheep", 70.0);
            put("WildBoar", 15.0);
            put("Buffalo", 10.0);
            put("Duck", 40.0);
        }};
    }
}
