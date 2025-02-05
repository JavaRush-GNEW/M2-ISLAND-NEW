package Organism.Animal.Predator.Animals;

import Organism.Animal.Predator.Predator;

import java.util.HashMap;

public class Fox extends Predator {
    public Fox() {
        super("Лисиця", 8, 2, 2, 30);
        this.preyChances = new HashMap<>() {{
            put("Rabbit", 70.0);
            put("Mouse", 90.0);
            put("Duck", 60.0);
            put("Caterpillar", 40.0);
        }};
    }
}
