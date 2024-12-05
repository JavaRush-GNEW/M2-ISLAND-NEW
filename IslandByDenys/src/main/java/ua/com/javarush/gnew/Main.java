package ua.com.javarush.gnew;

import ua.com.javarush.gnew.Herbivores.Boar;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Создание 50 кабанов
        ArrayList<Boar> boars = Boar.createBoars(50);
        System.out.println("Создано кабанов: " + boars.size());

        // Удаление случайного кабана
        Boar.removeRandomBoar(boars);

        // Удаление еще одного кабана
        Boar.removeRandomBoar(boars);
    }
}