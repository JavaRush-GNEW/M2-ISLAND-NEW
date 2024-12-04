package game.entity.inhabitant;

import game.domain.Properties;
import game.entity.Direction;
import game.entity.Organism;
import game.entity.animal.Animal;
import game.entity.animal.Herbivorous;
import game.entity.island.Area;
import game.entity.OrganismProperty;
import game.utils.OrganismPropertyUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Properties(filename = "duck.json")
public class Duck extends Herbivorous {

    public static final OrganismProperty ORGANISM_PROPERTY;
    public static final Map<String, Integer> NUTRITION_MAP;

    static {
        try {
            ORGANISM_PROPERTY = OrganismPropertyUtil.readOrganismProp(Duck.class);
            NUTRITION_MAP = OrganismPropertyUtil.readNutritionInf("duck_nutrition.yaml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    ThreadLocalRandom random = ThreadLocalRandom.current();

    public Duck() {
    }

    @Override
    public String toString() {
        return "Duck";
    }

    @Override
    public OrganismProperty getProperties() {
        return ORGANISM_PROPERTY;
    }

    @Override
    public Map<String, Integer> getNutritionMap() {
        return NUTRITION_MAP;
    }

    @Override
    public String getImage() {
        return "\uD83E\uDD86";
    }

    //TODO: move method to and read properties in animal class
    // use annotation isHunter to chose nutrition set(Animals, Plants, both?)

//    @Override
//    public void eat(Area area) {
//
//        removeAnimalFromArea(area);
//        //System.out.println("Duck: Yammy grass");
//        //System.out.println(getHealthPoint());
//    }

//    private void removeAnimalFromArea(Area area) {
//        Map<String, Set<Animal>> animalsAmountPerArea = area.getAnimalMap();
//        Iterator<Map.Entry<String, Set<Animal>>> iteratorMap = animalsAmountPerArea.entrySet().iterator();
//
//        while (iteratorMap.hasNext()) {
//            Map.Entry<String, Set<Animal>> next = iteratorMap.next();
//
//            if (NUTRITION_MAP.containsKey(next.getKey())) {
//                //System.out.println("eaten: " + next.getKey());
//                //animalMap.get(next.getKey()).remove(next.getValue());
//                Integer eatingProbability = NUTRITION_MAP.get(next.getKey());
//                Set<Animal> animalsAmount = animalsAmountPerArea.get(next.getKey());
//                if (!animalsAmount.isEmpty()) {
//                    Iterator<Animal> iteratorSet = animalsAmount.iterator();
//                    if (iteratorSet.hasNext()) {
//                        Animal animal = iteratorSet.next();
//                        if (probabilityOfEating(eatingProbability)) {
//                            //updateHealthStatus(animal.getProperties().getWeight());
//                            updateHealthStatus(animal.getProperties().getWeight());
//                            iteratorSet.remove();
//                            //updateHealthStatus(animal.getProperties().getWeight());
//                        }
//                    }
//                    break;
//                }
//            }
//        }
////        // Print the map to see the result
////        animalsAmountPerArea.forEach((k, v) -> {
////            System.out.println("Key: " + k);
////            v.forEach(animal ->
////                    System.out.println("Animal: " + animal.toString()));
////        });
//    }
//
//    private boolean probabilityOfEating(Integer eatingProbability) {
//        int nextInt = random.nextInt(0, 100);
//        //System.out.println(nextInt + ": " + eatingProbability);
//        return random.nextInt(0, 100) <= eatingProbability ? true : false;
//    }
//
//    private void updateHealthStatus(double animalweight) {
//        double achievedHealthPoint =(animalweight * 100)/ ORGANISM_PROPERTY.getMaxFoodWeight();
//        int achievedHealthPointRound = (int) Math.round(achievedHealthPoint);
//        this.healthPoint = this.healthPoint + achievedHealthPointRound;
//        //System.out.println(animalweight);
//        //System.out.println("achievedHealthPoint:" + achievedHealthPoint + "healthPoint:" + this.healthPoint);
//    }
//
//    private void die() {
//    }

    @Override
    public Duck reproduce() {
        boolean condition = healthPoint > 25;
        return condition ? new Duck() : null;
    }

    @Override
    public void move(Area area) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int nextInt = random.nextInt(0, ORGANISM_PROPERTY.getMaxStep());
        Direction direction = chooseDirection();

        //islandBoundary
        //maxAreaAmount

        int coordinateX = area.getCoordinateX();
        int coordinateY = area.getCoordinateY();

        int maxCellQuantity = ORGANISM_PROPERTY.getMaxCellQuantity();
        int newCoordinateX;
        int newCoordinateY;

        switch (direction) {
            case UP: {
                newCoordinateY = coordinateY - direction.getDeltaY();
                if (newCoordinateY > 0) {

                }
            }
        }
    }
}
