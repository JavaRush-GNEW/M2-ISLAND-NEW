package Organism.Animal.Predator.Animals;

import Organism.Animal.Predator.Predator;

import java.util.HashMap;

public class Python extends Predator {
    public Python() {
        super("Удав", 15, 3, 1, 30);
        this.preyChances = new HashMap<>() {{
            put("Fox", 15.0);
            put("Rabbit", 20.0);
            put("Mouse", 40.0);
            put("Duck", 10.0);
        }};
    }
}
