package entities.predators;

import annotations.LoadProperties;
import entities.Animal;
import entities.herbivores.*;
import map.Location;
import utils.AnnotationProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bear extends Predator{

    @LoadProperties.LoadFromJSON(key="REPRODUCE_PROBABILITY")
    private static double REPRODUCE_PROBABILITY;

    @LoadProperties.LoadFromJSON(key="ANIMAL_PER_CEIL")
    public static int ANIMAL_PER_CEIL;

    @LoadProperties.LoadFromJSON(key="WEIGHT")
    private static double WEIGHT;

    @LoadProperties.LoadFromJSON(key="MAX_SATURATION")
    private static double MAX_SATURATION;

    @LoadProperties.LoadFromJSON(key="MAX_STEPS")
    private static int MAX_STEPS;

    private static final Map<Class<? extends Animal>, Double> PREY_PROBABILITIES = new HashMap<>();

    static {
        PREY_PROBABILITIES.put(Horse.class, 0.4);
        PREY_PROBABILITIES.put(Deer.class, 0.8);
        PREY_PROBABILITIES.put(Rabbit.class, 0.8);
        PREY_PROBABILITIES.put(Mouse.class, 0.9);
        PREY_PROBABILITIES.put(Goat.class, 0.7);
        PREY_PROBABILITIES.put(Sheep.class, 0.7);
        PREY_PROBABILITIES.put(Boar.class, 0.5);
        PREY_PROBABILITIES.put(Buffalo.class, 0.2);
        PREY_PROBABILITIES.put(Duck.class, 0.1);
        AnnotationProcessor.initializeFromJSON(Bear.class);
    }

    public Bear(Location currentLocation) {
        super(WEIGHT, MAX_STEPS, MAX_SATURATION, currentLocation,PREY_PROBABILITIES);

    }


    @Override
    public void reproduce() {
        Set<Animal> wolves = currentLocation.getResidents().get(Bear.class);
        if (wolves.size()>1){
            if(random.nextInt(100)<REPRODUCE_PROBABILITY*100){
                if (health > HEALTH_TO_REPRODUCE) {
                    decreaseHealth(10);
                    currentLocation.addAnimal(new Bear(currentLocation));
                }
            }
            checkOnDeath();
        }

    }
}
