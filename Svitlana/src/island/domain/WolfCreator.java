package island.domain;

import island.domain.organism.Wolf;

public class WolfCreator extends OrganismCreator{
    @Override
    public Wolf createOrganism() {
        return new Wolf();
    }
}
