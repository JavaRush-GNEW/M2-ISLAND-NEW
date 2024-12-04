package game.entity.inhabitant;

import game.domain.Properties;
import game.entity.Direction;
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
    public String getImage() {
        return "\uD83E\uDD86";
    }

    //TODO: move method to and read properties in animal class
    // use annotation isHunter to chose nutrition set(Animals, Plants, both?)

    @Override
    public void eat(Area area) {

        Map<String, Set<Animal>> animalsAmountPerArea = area.getAnimalMap();
        Iterator<Map.Entry<String, Set<Animal>>> iteratorMap = animalsAmountPerArea.entrySet().iterator();

            while(iteratorMap.hasNext()){
                Map.Entry<String, Set<Animal>> next = iteratorMap.next();

                if(NUTRITION_MAP.containsKey(next.getKey())){
                    //System.out.println("eaten: " + next.getKey());
                    //animalMap.get(next.getKey()).remove(next.getValue());
                    Set<Animal> animalsAmount = animalsAmountPerArea.get(next.getKey());
                    Iterator<Animal> iteratorSet = animalsAmount.iterator();
                    if(iteratorSet.hasNext()){
                        iteratorSet.next();
                        iteratorSet.remove();
                    }
                    break;
                }
            }
        //System.out.println("Duck: Yammy grass");
    }

    @Override
    public Duck reproduce() {
        boolean condition = true;
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
            case UP: { newCoordinateY = coordinateY - direction.getDeltaY();
                        if (newCoordinateY > 0){

                        }
            }
        }
    }
}
