package ua.com.javarush.gnew.entity;

import java.util.Random;

public abstract class Animal extends Organism {
    protected int lifePower = 50;

    public int getLifePower() {
        return lifePower;
    }

    public void setLifePower(int lifePower) {
        this.lifePower = lifePower;
    }

    public int move() {
        lifePower -=5;
        if (lifePower <= 0) {
            die();
        }
        return new Random().nextInt(3) + 1;
    }


}
