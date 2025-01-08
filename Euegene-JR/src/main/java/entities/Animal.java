package entities;

import context.ApplicationContext;
import map.GameMap;
import map.Location;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Organism{
    protected double health = 100;
    protected boolean isAlive;
    protected ThreadLocalRandom random = ThreadLocalRandom.current();
    protected Location currentLocation;
    protected double weight;
    protected double saturation;
    protected int max_steps;
    protected final static int FULL_SATURATION_BONUS = 20;
    protected final static int HEALTH_TO_REPRODUCE = 75;
    private final static int MOVE_COST = 10;


    public Animal(double weight, int max_steps, double saturation, Location currentLocation) {
        this.currentLocation = currentLocation;
        this.isAlive = true;
        this.weight = weight;
        this.saturation = saturation;
        this.max_steps = max_steps;
    }

    public void performAction() {
        int action = random.nextInt(3);
        switch (action) {
            case 0 -> eat();
            case 1 -> move();
            case 2 -> reproduce();
        }
        checkOnDeath();
    }

    public void move() {
        if (max_steps==0){
            return;
        }
        int steps = random.nextInt(max_steps) + 1;
        for (int i = 0; i < steps; i++) {
            Location newLocation = currentLocation.getValidLocation();
                if (newLocation.hasSpace()) {
                    currentLocation.removeAnimal(this);
                    newLocation.addAnimal(this);
                    this.currentLocation = newLocation;
                }
            }
        decreaseHealth(MOVE_COST);
        checkOnDeath();

    }

    public abstract void eat();


    public void increaseHealth(double value) {
        if (value>0){
            health+=value;
            if (health>100){
                health=100;
            }
        }
    }

    public void decreaseHealth(double amount) {
        this.health -= amount;
        if (this.health <= 0) {
            this.isAlive = false;
        }
    }

    public void checkOnDeath(){
        if(!isAlive){
            currentLocation.removeAnimal(this);
        }
    }

    public double getWeight() {
        return weight;
    }

    public boolean isAlive() {
        return this.health>=0;
    }


}
