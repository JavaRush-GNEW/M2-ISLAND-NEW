package game.entity.inhabitant;

import game.domain.Properties;
import game.entity.OrganismProperty;
import game.entity.plant.Plant;
import game.utils.OrganismPropertyUtil;
import java.io.IOException;

@Properties(filename = "grass.json")
public class Grass extends Plant {
    public Grass() {}

    public static final OrganismProperty ORGANISM_PROPERTY;

    static {
        try {
            ORGANISM_PROPERTY = OrganismPropertyUtil.readOrganismProp(Grass.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrganismProperty getProperties() {
        return ORGANISM_PROPERTY;
    }

    @Override
    public String getImage() {
        return "\uD83C\uDF31";
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
}
