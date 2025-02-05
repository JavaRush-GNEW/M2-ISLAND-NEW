package Organism.Animal.Predator.Animals;

import Organism.Animal.Predator.Predator;

import java.util.HashMap;

public class Eagle extends Predator {
    public Eagle() {
        super("Орел", 6, 1, 3, 20);
        this.preyChances = new HashMap<>() {{
            put("Fox", 10.0);
            put("Rabbit", 90.0);
            put("Mouse", 90.0);
            put("Duck", 80.0);
        }};
    }
}
