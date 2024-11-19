package ua.com.javarush.gnew.entity;

import java.util.Random;

public abstract class Animal extends Organism {
    protected static int lifePower = 50;

    public static int getLifePower() {
        return lifePower;
    }

    public static int move() {
        lifePower -=5;
        if (lifePower <= 0) {
            die();
        }
        return new Random().nextInt(3) + 1;
    }


}
