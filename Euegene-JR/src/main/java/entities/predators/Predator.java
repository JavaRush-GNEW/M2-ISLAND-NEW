package entities.predators;

import entities.Animal;
import map.Location;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    private final Map<Class<? extends Animal>, Double> preyProbabilityMap;


    public Predator(double weight, int max_steps, double saturation, Location currentLocation,
                    Map<Class<? extends Animal>, Double> preyProbabilityMap) {
        super(weight,max_steps,saturation,currentLocation);
        this.preyProbabilityMap = preyProbabilityMap;
    }

    @Override
    public void eat() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<Animal> potentialPreySetCopy = new HashSet<>(getPreySet());
        Animal prey = potentialPreySetCopy.stream().findAny().orElse(null);
        if (prey != null) {
            synchronized (prey) {
                double probability = preyProbabilityMap.getOrDefault(prey.getClass(), 0.0);
                if (random.nextInt(100) < probability * 100) {
                    currentLocation.removeAnimal(prey);
                    double healthGained = (prey.getWeight() / saturation) * FULL_SATURATION_BONUS;
                    healthGained = Math.min(healthGained, FULL_SATURATION_BONUS);
                    increaseHealth(healthGained);
                } else {
                    decreaseHealth(10);

                }
            }
            } else {
                decreaseHealth(5);
            }
            checkOnDeath();
    }



    
    public Set<Animal> getPreySet(){
        // Проходимо по всіх мешканцях локації
        Set<Animal> potentialPreySet = new HashSet<>();
        for (Map.Entry<Class<? extends Animal>, Set<Animal>> entry : currentLocation.getResidents().entrySet()) {
            // Ігноруємо вовків, адже вони не можуть їсти свій вид
            if (entry.getKey() == this.getClass()) {
                continue;
            }

            // Додаємо всі потенційні жертви цього класу до множини
            potentialPreySet.addAll(entry.getValue());
        }
        return potentialPreySet;
    }





}
