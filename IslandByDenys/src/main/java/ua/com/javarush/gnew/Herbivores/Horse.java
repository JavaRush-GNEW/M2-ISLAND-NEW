package ua.com.javarush.gnew.Herbivores;

import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.animal.Animals;

import java.util.ArrayList;

public class Horse extends Animals {
    public Horse(int weight, int saturation, int speed) {
        super(weight, saturation, speed);
    }

    private static final ArrayList<Boar> horse = new ArrayList<>();
    private int x;
    private int y;

    public Horse(int weight, int speed, int saturation, int x, int y) {
        super(weight, saturation, speed);
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean Eating() {
        return false;
    }

    @Override
    public void move(Field field, int deltaX, int deltaY) {
    }

    @Override
    public boolean reproduction() {
        return false;
    }

    @Override
    public boolean death() {
        return false;
    }
}
