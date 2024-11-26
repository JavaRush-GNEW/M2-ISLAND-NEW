package ua.com.javarush.gnew.entity;

public abstract class Organism {
    private static boolean alive = true;
    private final int maxCellResidents;
    public double weight;

    public Organism(int maxCellResidents, double initialWeight) {
        this.maxCellResidents = maxCellResidents;
        this.weight = initialWeight;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
        checkSatiation();
    }

    public boolean isAlive() {
        return alive;
    }

    public static void die() {
        alive = false;
    }
    public int getMaxCellResidents() {
        return maxCellResidents;
    }

    public void checkSatiation() {

    }
}
