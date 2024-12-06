package game.entity.animal;

import game.domain.AnimalType;
import game.entity.Organism;
import game.entity.island.Area;

@AnimalType(isHunter = false)
public abstract class Herbivorous extends Animal implements Organism {

}
