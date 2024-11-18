package ua.com.javarush.gnew.entity;

public abstract class Organism {
    private boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        this.alive = false;
    }
}
