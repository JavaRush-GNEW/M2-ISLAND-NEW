package game.entity.animal;

import game.entity.Movable;
import game.entity.OrganismProperty;
import game.entity.island.Area;
import game.entity.Direction;
import game.entity.Organism;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Organism, Movable {
    private final String id;
    protected int healthPoint;
    ThreadLocalRandom random = ThreadLocalRandom.current();

    protected Animal(){
        this.healthPoint = 100;
        this.id = UUID.randomUUID().toString();
    }

    protected int getHealthPoint() {
        return healthPoint;
    }

    public abstract OrganismProperty getProperties();

    public abstract Map<String, Integer> getNutritionMap();

    public void eat(Area area) {

        removeAnimalFromArea(area);
        System.out.println(getHealthPoint());
    }

    private void removePlantFromArea(Area area) {}

    private void removeAnimalFromArea(Area area) {
        Map<String, Set<Animal>> animalsAmountPerArea = area.getAnimalMap();
        Iterator<Map.Entry<String, Set<Animal>>> iteratorMap = animalsAmountPerArea.entrySet().iterator();

        while (iteratorMap.hasNext()) {
            Map.Entry<String, Set<Animal>> next = iteratorMap.next();

            if (getNutritionMap().containsKey(next.getKey())) {
                System.out.println("eaten: " + next.getKey());
                //animalMap.get(next.getKey()).remove(next.getValue());
                Integer eatingProbability = getNutritionMap().get(next.getKey());
                Set<Animal> animalsAmount = animalsAmountPerArea.get(next.getKey());
                if (!animalsAmount.isEmpty()) {
                    Iterator<Animal> iteratorSet = animalsAmount.iterator();
                    if (iteratorSet.hasNext()) {
                        Animal animal = iteratorSet.next();
                        if (probabilityOfEating(eatingProbability)) {
                            //updateHealthStatus(animal.getProperties().getWeight());
                            updateHealthStatus(animal.getProperties().getWeight());
                            iteratorSet.remove();
                            //updateHealthStatus(animal.getProperties().getWeight());
                        }
                    }
                }
                break;
            }
        }
//        // Print the map to see the result
//        animalsAmountPerArea.forEach((k, v) -> {
//            System.out.println("Key: " + k);
//            v.forEach(animal ->
//                    System.out.println("Animal: " + animal.toString()));
//        });
    }

    private boolean probabilityOfEating(Integer eatingProbability) {
        int nextInt = random.nextInt(0, 100);
        //System.out.println(nextInt + ": " + eatingProbability);
        return random.nextInt(0, 100) <= eatingProbability ? true : false;
    }

    private void updateHealthStatus(double animalweight) {
        double achievedHealthPoint =(animalweight * 100)/ getProperties().getMaxFoodWeight();
        int achievedHealthPointRound = (int) Math.round(achievedHealthPoint);
        this.healthPoint = this.healthPoint + achievedHealthPointRound;
        //System.out.println(animalweight);
        //System.out.println("achievedHealthPoint:" + achievedHealthPoint + "healthPoint:" + this.healthPoint);
    }

    private void die() {
    }


    protected Direction chooseDirection(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        return direction;
    }
}
