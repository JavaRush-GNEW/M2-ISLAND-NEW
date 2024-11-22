package game.entity.animal;

import game.entity.OrganismProperty;
import game.entity.island.Area;
import game.entity.Direction;
import game.entity.Organism;
import game.utils.OrganismPropertyUtil;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Organism {
    private final String id;
    protected int healthPoint;

    protected Animal(){
        this.healthPoint = 100;
        this.id = UUID.randomUUID().toString();
    }

    public abstract OrganismProperty getProperties();
    public abstract void eat(Area area);


    private Direction chooseDirection(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        return direction;
    }
}
