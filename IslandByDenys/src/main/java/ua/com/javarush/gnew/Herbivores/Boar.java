package ua.com.javarush.gnew.Herbivores;


import lombok.Getter;
import lombok.Setter;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Field.Fieldable;
import ua.com.javarush.gnew.animal.Animals;

import java.util.ArrayList;
import java.util.Random;

@Setter
@Getter
public class Boar extends Animals implements Fieldable {
    private static final ArrayList<Boar> boars = new ArrayList<>();
    private int x;
    private int y;

    public Boar(int weight, int speed, int saturation, int x, int y) {
        super(weight, saturation, speed);
        this.x = x;
        this.y = y;
    }

    public Boar(int weight, int speed, int saturation) {
        super(weight, saturation, speed);
    }

    public static ArrayList<Boar> createBoars(int count) {
        for (int i = 0; i < count; i++) {
            boars.add(new Boar(400, 2, 50));
        }
        return boars;
    }

    public static void removeRandomBoar(ArrayList<Boar> boars) {
        if (!boars.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(boars.size());
            boars.remove(index);
            System.out.println("Кабан удалён. Осталось кабанов: " + boars.size());
        } else {
            System.out.println("Список кабанов пуст!");
        }
    }

    @Override
    public boolean Eating() {
        return false;
    }
    @Override
    public void move(Field field, int deltaX, int deltaY) {
        int newX = x + deltaX;
        int newY = y + deltaY;

        if (newX >= 0 && newX < field.getRows() && newY >= 0 && newY < field.getColumns()) {
            field.SaveInField(x, y, null);
            x = newX;
            y = newY;
            field.SaveInField(x, y, this);
            System.out.println("Кабан переместился на (" + x + ", " + y + ")");
        } else {
            System.out.println("Кабан не может выйти за пределы поля!");
        }
    }
        @Override
        public boolean reproduction () {
            return false;
        }

        @Override
        public boolean death () {
            removeRandomBoar(boars);
            System.out.println("Кабан умер.");
            return true;
        }
    }

