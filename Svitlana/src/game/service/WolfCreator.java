package game.service;

import game.entity.inhabitant.Wolf;

public class WolfCreator extends OrganismCreator{
    @Override
    public Wolf createOrganism() {
        return new Wolf();
    }
}
