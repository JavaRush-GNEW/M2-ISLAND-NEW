package game.entity.animal;

import game.domain.AnimalType;
import game.entity.Movable;
import game.entity.OrganismProperty;
import game.entity.island.Area;
import game.entity.Direction;
import game.entity.Organism;
import game.entity.island.Island;
import game.entity.plant.Plant;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static game.entity.island.Island.GAME_PROPERTY;

public abstract class Animal implements Organism, Movable {
    private final String id;
    protected int healthPoint;
    ThreadLocalRandom random = ThreadLocalRandom.current();
    Area[][] areas;

    int coordinateX;
    int coordinateY;

    protected Animal(){
        this.healthPoint = 100;
        this.id = UUID.randomUUID().toString();
        this.areas = Island.getArea();
    }

    protected int getHealthPoint() {
        return healthPoint;
    }

    public abstract OrganismProperty getProperties();

    public abstract Map<String, Integer> getNutritionMap();

    public void eat(Area area) {

        Class aClass = this.getClass();
        AnimalType animalAnnotation = (AnimalType)aClass.getAnnotation(AnimalType.class);

        if(this instanceof Predator){
            removeAnimalFromArea(area);
        }
        if(this instanceof Herbivorous){
            if (animalAnnotation.isHunter()){
                removeAnimalFromArea(area);
                removePlantFromArea(area);
            } else removePlantFromArea(area);
        }

        System.out.println(getHealthPoint());

    }

    private void removePlantFromArea(Area area) {
        System.out.println("Removing plant from " + area);

        Map<String, Set<Plant>> plantAmountPerArea = area.getPlantMap();
        Iterator<Map.Entry<String, Set<Plant>>> iteratorMap = plantAmountPerArea.entrySet().iterator();

        while (iteratorMap.hasNext()) {
            Map.Entry<String, Set<Plant>> next = iteratorMap.next();

            if (getNutritionMap().containsKey(next.getKey())) {
                System.out.println("eaten: " + next.getKey());
                //animalMap.get(next.getKey()).remove(next.getValue());
                Integer eatingProbability = getNutritionMap().get(next.getKey());
                Set<Plant> plantsAmount = plantAmountPerArea.get(next.getKey());
                if (!plantsAmount.isEmpty()) {
                    Iterator<Plant> iteratorSet = plantsAmount.iterator();
                    if (iteratorSet.hasNext()) {
                        Plant plant = iteratorSet.next();
                        if (probabilityOfEating(eatingProbability)) {
                            //updateHealthStatus(animal.getProperties().getWeight());
                            updateHealthStatus(plant.getProperties().getWeight());
                            iteratorSet.remove();
                            //updateHealthStatus(animal.getProperties().getWeight());
                        }
                    }
                }
                break;
            }
        }

    }

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
    }

    private boolean probabilityOfEating(Integer eatingProbability) {
        int nextInt = random.nextInt(0, 100);
        //System.out.println(nextInt + ": " + eatingProbability);
        return random.nextInt(0, 100) <= eatingProbability ? true : false;
    }

    private void updateHealthStatus(double organismWeight) {
        double achievedHealthPoint =(organismWeight * 100)/ getProperties().getMaxFoodWeight();
        int achievedHealthPointRound = (int) Math.round(achievedHealthPoint);
        this.healthPoint = this.healthPoint + achievedHealthPointRound / 100;
        //System.out.println(animalweight);
        //System.out.println("achievedHealthPoint:" + achievedHealthPoint + "healthPoint:" + this.healthPoint);
    }

    private void die() {
    }


    public void move(Area area) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int maxStepAmount = random.nextInt(0, getProperties().getMaxStep());
        int maxQuantityPerArea = getProperties().getMaxCellQuantity();
        Direction direction = chooseDirection();

        this.coordinateX = area.getCoordinateX();
        this.coordinateY = area.getCoordinateY();

        int newCoordinateX;
        int newCoordinateY;

        //islandBoundary
        //islandWidth
        //islandHeight

        int maxCellQuantity = getProperties().getMaxCellQuantity();

        for(int i = 0; i < maxStepAmount; i++){
            switch (direction) {
                case UP: {
                    newCoordinateY = coordinateY - direction.getDeltaY();
                    if (newCoordinateY > 0) {

                    }
                }
                case DOWN:{}
                case LEFT: {
                }
                case RIGHT: {}
            }
        }

    }

    protected Direction chooseDirection(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        return direction;
    }


}
