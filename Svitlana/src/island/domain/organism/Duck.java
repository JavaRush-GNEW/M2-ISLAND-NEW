package island.domain.organism;

import island.domain.Area;
import island.domain.OrganismProperty;
import island.domain.Properties;
import island.utils.OrganismPropertyUtil;

@Properties(filename = "duck.json")
public class Duck extends Herbivorous{

    private static final OrganismProperty ORGANISM_PROPERTY = OrganismPropertyUtil.parse(Duck.class);
    @Override
    public void getProperties() {

    }

    @Override
    public void populate() {

    }

    @Override
    public void eat(Area area) {

    }

    @Override
    public Animal reproduce() {
        return null;
    }

    @Override
    public void move() {

    }
}
