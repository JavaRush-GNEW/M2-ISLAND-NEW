package Organism.Animal.Omnivore.Animals;

import Organism.Animal.Omnivore.Omnivore;

import java.util.HashMap;

public class Duck extends Omnivore {
    public Duck() {
        super("Качка", 1, 0.15, 4, 200);
        this.preyChances = new HashMap<>() {{
            put("Caterpillar", 90.0);
        }};
    }
}
