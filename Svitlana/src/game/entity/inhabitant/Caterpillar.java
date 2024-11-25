package game.entity.inhabitant;

import game.domain.Properties;
import game.entity.Organism;
import game.entity.OrganismProperty;
import game.entity.animal.Animal;
import game.entity.animal.Herbivorous;
import game.entity.island.Area;
import game.utils.OrganismPropertyUtil;

import java.io.IOException;
import java.util.Map;

@Properties(filename = "caterpillar.json")
public class Caterpillar extends Herbivorous {
    public Caterpillar() {
    }

    public static final OrganismProperty ORGANISM_PROPERTY;
    public static final Map<Class<? extends Organism>, Integer> NUTRITION_MAP;

    static {
        try {
            ORGANISM_PROPERTY = OrganismPropertyUtil.readOrganismProp(Duck.class);
            NUTRITION_MAP = OrganismPropertyUtil.readNutritionInf("duck_nutrition.yaml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrganismProperty getProperties() {
        return ORGANISM_PROPERTY;
    }

    @Override
    public void eat(Area area) {
        System.out.println("Awesome air");
    }

    @Override
    public Animal reproduce() {

        boolean condition = true;
        return condition ? new Caterpillar() : null;
    }

    @Override
    public void move() {

    }
}
