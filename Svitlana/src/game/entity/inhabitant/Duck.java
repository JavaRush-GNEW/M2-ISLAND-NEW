package game.entity.inhabitant;

import game.domain.AnimalType;
import game.domain.Properties;
import game.entity.animal.Herbivorous;
import game.entity.OrganismProperty;
import game.utils.OrganismPropertyUtil;
import java.io.IOException;
import java.util.Map;

@AnimalType(isHunter = true)
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

    @Override
    public String toString() {
        return "Duck";
    }

    @Override
    public Duck reproduce() {
        if(healthPoint > 25){
            healthPoint = healthPoint - 25;
            return  new Duck();
        }
        else return null;
    }

}
