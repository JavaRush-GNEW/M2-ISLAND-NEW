package game.service;

import game.entity.inhabitant.Duck;

public class DuckCreator extends OrganismCreator{
    @Override
    public Duck createOrganism() {

        return new Duck();
    }
}
