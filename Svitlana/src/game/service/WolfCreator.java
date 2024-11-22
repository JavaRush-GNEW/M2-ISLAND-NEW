package game.service;

import game.entity.animal.Wolf;

public class WolfCreator extends OrganismCreator{
    @Override
    public Wolf createOrganism() {
        return new Wolf();
    }
}
