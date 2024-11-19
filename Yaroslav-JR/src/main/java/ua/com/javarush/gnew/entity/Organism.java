package ua.com.javarush.gnew.entity;

public abstract class Organism {
    private static boolean alive = true;

    public static boolean isAlive() {
        return alive;
    }

    public static void die() {
        alive = false;
    }
}
