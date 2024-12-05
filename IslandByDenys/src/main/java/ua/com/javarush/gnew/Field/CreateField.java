package ua.com.javarush.gnew.Field;

import ua.com.javarush.gnew.Herbivores.Boar;

import java.util.ArrayList;
import java.util.Random;

public class CreateField {
    public static void main(String[] args) {
        Field myField = new Field(100, 100);


        ArrayList<Boar> boars = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Boar boar = new Boar(400, 2, 50, i % 100, i % 100);
            boars.add(boar);
            myField.SaveInField(boar.getX(), boar.getY(), boar);
        }


        Random random = new Random();
        for (Boar boar : boars) {
            int deltaX = random.nextInt(3) - 1; // -1, 0 или 1
            int deltaY = random.nextInt(3) - 1;
            boar.move(myField, deltaX, deltaY);
        }




        //Boar firstBoar = boars.get(0);
        //System.out.println("Кабан 1 теперь на (" + firstBoar.getX() + ", " + firstBoar.getY() + ")");
    }
}

