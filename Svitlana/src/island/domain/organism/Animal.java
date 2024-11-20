package island.domain.organism;

import island.domain.Area;
import island.domain.Direction;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Organism {
    private final String id;
    protected int healthPoint;

    protected Animal(){
        this.healthPoint = 100;
        this.id = UUID.randomUUID().toString();
    }

    public abstract void eat(Area area);
    public abstract Animal reproduce();
    public abstract void move();

    private Direction chooseDirection(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        return direction;
    }
}
