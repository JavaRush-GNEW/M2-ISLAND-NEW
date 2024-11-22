package game.entity.animal;

import game.domain.Properties;
import game.entity.island.Area;
import game.entity.Organism;
import game.entity.OrganismProperty;
import game.utils.OrganismPropertyUtil;

import java.io.IOException;
import java.util.Map;

@Properties(filename = "duck.json")
public class Duck extends Herbivorous {
    public Duck() {
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

//    @Override
//    public void populate() {
//
//    }

    @Override
    public void eat(Area area) {

    }

    @Override
    public Duck reproduce() {
        boolean condition = true;
        return condition ? new Duck() : null;
    }

    @Override
    public void move() {

    }
}
