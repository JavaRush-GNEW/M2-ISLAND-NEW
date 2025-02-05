package Organism.Animal.Predator.Animals;

import Organism.Animal.Predator.Predator;

import java.util.HashMap;

public class Bear extends Predator {
    public Bear() {
        super("Ведмідь", 500, 80, 2, 5);
        this.preyChances = new HashMap<>() {{
            put("Python", 80.0);
            put("Horse", 40.0);
            put("Deer", 80.0);
            put("Rabbit", 80.0);
            put("Mouse", 90.0);
            put("Goat", 70.0);
            put("Sheep", 70.0);
            put("WildBoar", 50.0);
            put("Buffalo", 20.0);
            put("Duck", 10.0);
        }};
    }
}
