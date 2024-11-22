package game.service;

import game.entity.Organism;
import game.entity.animal.Duck;

public class DuckCreator extends OrganismCreator{
    @Override
    public Duck createOrganism() {

        return new Duck();
    }
}
