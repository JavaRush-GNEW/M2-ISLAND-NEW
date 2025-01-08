package entities.herbivores;

import entities.Animal;
import entities.plants.Plant;
import map.Location;

import java.util.Iterator;
import java.util.Set;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int max_steps, double saturation, Location currentLocation) {
        super(weight, max_steps, saturation, currentLocation);
    }

    @Override
    public void eat() {
        Set<Plant> plants = currentLocation.getPlants();
        synchronized (plants) {
            if (plants.isEmpty()) {

                decreaseHealth(5);
                return;
            }
            int foodRequired = (int) Math.ceil((double) this.saturation / Plant.WEIGHT);
            int foodEaten = 0;

            Iterator<Plant> iterator = plants.iterator();
            while (iterator.hasNext() && foodEaten < foodRequired) {
                iterator.next();
                iterator.remove();
                foodEaten++;
            }

            double foodWeightEaten = foodEaten * Plant.WEIGHT;
            double healthGained = foodWeightEaten / this.saturation * FULL_SATURATION_BONUS;
            this.increaseHealth((int) Math.round(Math.min(healthGained, FULL_SATURATION_BONUS)));

        }
    }
}
