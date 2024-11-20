package ua.com.javarush.gnew.entity;

public abstract class Organism {
    private static boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public static void die() {
        alive = false;
    }
}
