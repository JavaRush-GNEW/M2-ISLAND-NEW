package game.entity.inhabitant;

import game.domain.AnimalType;
import game.domain.Properties;
import game.entity.OrganismProperty;
import game.entity.animal.Animal;
import game.entity.animal.Herbivorous;
import game.entity.island.Area;
import game.utils.OrganismPropertyUtil;
import java.io.IOException;
import java.util.Map;

@AnimalType(isHunter = false)
@Properties(filename = "caterpillar.json")
public class Caterpillar extends Herbivorous {

    public static final OrganismProperty ORGANISM_PROPERTY;
    public static final Map<String, Integer> NUTRITION_MAP;

    static {
        try {
            ORGANISM_PROPERTY = OrganismPropertyUtil.readOrganismProp(Caterpillar.class);
            NUTRITION_MAP = OrganismPropertyUtil.readNutritionInf("caterpillar_nutrition.yaml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Caterpillar() {
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
        return "\uD83D\uDC1B";
    }

    @Override
    public String toString() {
        return "Caterpillar";
    }

    @Override
    public Caterpillar reproduce() {
        if(healthPoint > 25){
            healthPoint = healthPoint - 25;
            return  new Caterpillar();
        }
        else return null;
    }

    @Override
    public void move(Area area) {

    }
}
