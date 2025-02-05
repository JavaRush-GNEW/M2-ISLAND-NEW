package entities.herbivores;

import annotations.LoadProperties;
import entities.Animal;
import entities.plants.Plant;
import map.Location;
import utils.AnnotationProcessor;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore{
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

    public Duck(Location currentLocation) {
        super(WEIGHT,MAX_STEPS,MAX_SATURATION, currentLocation);
    }
    static {
        AnnotationProcessor.initializeFromJSON(Duck.class);
    }

    @Override
    public void eat() {
        Set<Animal> caterpillarsCopy;
        ThreadLocalRandom random1 = ThreadLocalRandom.current();
        if(random1.nextInt(2)==1){
            super.eat();
        }else {
            synchronized (currentLocation.getResidents()){
                HashMap<Class<? extends Animal>, Set<Animal>> residents = currentLocation.getResidents();
                Set<Animal> caterpillars = residents.get(Caterpillar.class);
                caterpillarsCopy= new HashSet<>(caterpillars);
            }
            if (caterpillarsCopy.isEmpty()) {
                decreaseHealth(5);
                return;
            }
            int foodRequired = (int) Math.ceil(this.saturation / Caterpillar.WEIGHT);
            int foodEaten = 0;
            Iterator<Animal> iterator = caterpillarsCopy.iterator();
            while (iterator.hasNext() && foodEaten < foodRequired) {
                iterator.next();
                iterator.remove();
                foodEaten++;
            }
            double foodWeightEaten = foodEaten * Caterpillar.WEIGHT;
            double healthGained = foodWeightEaten / this.saturation * FULL_SATURATION_BONUS;
            this.increaseHealth((int) Math.round(Math.min(healthGained, FULL_SATURATION_BONUS)));
        }
    }


    @Override
    public void reproduce() {
        Set<Animal> sameResidents = currentLocation.getResidents().get(Duck.class);
        if (sameResidents.size()>1){
            if(random.nextInt(100)<REPRODUCE_PROBABILITY*100){
                if (health > HEALTH_TO_REPRODUCE) {
                    decreaseHealth(10);
                    currentLocation.addAnimal(new Duck(currentLocation));
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
