package entities.herbivores;

import annotations.LoadProperties;
import entities.Animal;
import entities.predators.Wolf;
import map.Location;
import utils.AnnotationProcessor;
import utils.JSONLoader;

import java.util.Set;

public class Rabbit extends Herbivore {
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

    public Rabbit(Location currentLocation) {
        super(WEIGHT,MAX_STEPS,MAX_SATURATION, currentLocation);
    }
    static {
        AnnotationProcessor.initializeFromJSON(Rabbit.class);
    }


    @Override
    public void reproduce() {
        Set<Animal> sameResidents = currentLocation.getResidents().get(Rabbit.class);
        if (sameResidents.size()>1){
        if(random.nextInt(100)<REPRODUCE_PROBABILITY*100){
            if (health > HEALTH_TO_REPRODUCE) {
                decreaseHealth(10);
                currentLocation.addAnimal(new Rabbit(currentLocation));
            }
            checkOnDeath();
            }
        }
    }

    public void checkOnDeath(){
        if(health<=0){
            currentLocation.removeAnimal(this);
        }
    }
}
