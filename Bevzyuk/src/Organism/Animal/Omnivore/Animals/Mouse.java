package Organism.Animal.Omnivore.Animals;

import Organism.Animal.Omnivore.Omnivore;

import java.util.HashMap;

public class Mouse extends Omnivore {
    public Mouse() {
        super("Миша", 0.05, 0.01, 1, 500);
        this.preyChances = new HashMap<>() {{
            put("Caterpillar", 90.0);
        }};
    }
}
