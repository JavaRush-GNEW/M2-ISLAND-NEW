package Organism.Animal.Omnivore.Animals;

import Organism.Animal.Omnivore.Omnivore;

import java.util.HashMap;

public class WildBoar extends Omnivore {
    public WildBoar() {
        super("Кабан", 400, 50, 2, 50);
        this.preyChances = new HashMap<>() {{
            put("Mouse", 50.0);
            put("Caterpillar", 90.0);
        }};
    }
}
