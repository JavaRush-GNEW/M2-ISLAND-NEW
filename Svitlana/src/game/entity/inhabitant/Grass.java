package game.entity.inhabitant;

import game.domain.Properties;
import game.entity.Organism;
import game.entity.OrganismProperty;
import game.entity.plant.Plant;
import game.utils.OrganismPropertyUtil;

import java.io.IOException;
import java.util.Map;

@Properties(filename = "grass.json")
public class Grass extends Plant {
    public Grass() {}

    public static final OrganismProperty ORGANISM_PROPERTY;
    //public static final Map<Class<? extends Organism>, Integer> NUTRITION_MAP;

    static {
        try {
            ORGANISM_PROPERTY = OrganismPropertyUtil.readOrganismProp(Grass.class);
            //NUTRITION_MAP = OrganismPropertyUtil.readNutritionInf("duck_nutrition.yaml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Grass";
    }

    @Override
    public Grass reproduce() {
        boolean condition = true;
        return condition ? new Grass() : null;
    }

    @Override
    public OrganismProperty getProperties() {
        return ORGANISM_PROPERTY;
    }

    @Override
    public String getImage() {
        return "\uD83C\uDF31";
    }
}
