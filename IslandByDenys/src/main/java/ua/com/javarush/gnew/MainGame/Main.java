package ua.com.javarush.gnew.MainGame;


import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Plant.Plant;
import ua.com.javarush.gnew.Predator.Bear;
import ua.com.javarush.gnew.Predator.Fox;
import ua.com.javarush.gnew.Predator.Wolf;
import ua.com.javarush.gnew.PropertiesLoader.ConfigLoader;
import ua.com.javarush.gnew.herbivore.*;
import java.util.Random;

public class Main implements MainSatellite{
    public static void main(String[] args) {
        Field field = new Field(50, 10);

        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            Wolf wolf = new Wolf(ConfigLoader.getProperty("wolf.name"), ConfigLoader.getDoubleProperty("wolf.weight"), ConfigLoader.getIntProperty("wolf.quantity"), ConfigLoader.getIntProperty("wolf.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, wolf, random);
        }
        for (int i = 0; i < 30; i++) {
            Fox fox = new Fox(ConfigLoader.getProperty("fox.name"), ConfigLoader.getDoubleProperty("fox.weight"), ConfigLoader.getIntProperty("fox.quantity"), ConfigLoader.getIntProperty("fox.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, fox, random);
        }
        for (int i = 0; i < 5; i++) {
            Bear bear = new Bear(ConfigLoader.getProperty("bear.name"), ConfigLoader.getDoubleProperty("bear.weight"), ConfigLoader.getIntProperty("bear.quantity"), ConfigLoader.getIntProperty("bear.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, bear, random);
        }

        for (int i = 0; i < 150; i++) {
            Rabbit rabbit = new Rabbit(ConfigLoader.getProperty("rabbit.name"), ConfigLoader.getDoubleProperty("rabbit.weight"), ConfigLoader.getIntProperty("rabbit.quantity"), ConfigLoader.getDoubleProperty("rabbit.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, rabbit, random);
        }

        for (int i = 0; i < 140; i++) {
            Sheep sheep = new Sheep(ConfigLoader.getProperty("sheep.name"), ConfigLoader.getDoubleProperty("sheep.weight"), ConfigLoader.getIntProperty("sheep.quantity"), ConfigLoader.getIntProperty("sheep.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, sheep, random);
        }
        for (int i = 0; i < 20; i++) {
            Horse horse = new Horse(ConfigLoader.getProperty("horse.name"), ConfigLoader.getDoubleProperty("horse.weight"), ConfigLoader.getIntProperty("horse.quantity"), ConfigLoader.getIntProperty("horse.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, horse, random);
        }
        for (int i = 0; i < 20; i++) {
            Deer deer = new Deer(ConfigLoader.getProperty("deer.name"), ConfigLoader.getDoubleProperty("deer.weight"), ConfigLoader.getIntProperty("deer.quantity"), ConfigLoader.getIntProperty("deer.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, deer, random);
        }
        for (int i = 0; i < 50; i++) {
            Boar boar = new Boar(ConfigLoader.getProperty("boar.name"), ConfigLoader.getDoubleProperty("boar.weight"), ConfigLoader.getIntProperty("boar.quantity"), ConfigLoader.getIntProperty("boar.foodNeeded"));
            MainSatellite.placeAnimalRandomly(field, boar, random);
        }

        for (int i = 0; i < 200; i++) {
            Plant plant = new Plant(ConfigLoader.getProperty("plants.name"), ConfigLoader.getDoubleProperty("plants.weight"), ConfigLoader.getIntProperty("plants.quantity"));
            MainSatellite.placePlantRandomly(field, plant, random);
        }

        Simulation simulation = new Simulation(field);
        simulation.start();

        System.out.println("Симуляция началась. На поле 30 волков, 30 лисиц, 5 медведей,  150 кроликов, 140 овец, 20 лошадей, 20 оленей, 50 кабанов и 200 растений.");
    }

}



