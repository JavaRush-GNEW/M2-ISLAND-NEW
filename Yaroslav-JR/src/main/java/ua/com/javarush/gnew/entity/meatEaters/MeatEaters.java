package ua.com.javarush.gnew.entity.meatEaters;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.chewingGrass.ChewingGrass;
import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Optional;
import java.util.Random;

public abstract class MeatEaters extends Animal {

    public static void eat(Cell cell) {
        Optional<ChewingGrass> prey = cell.getAnimals().stream()
                .filter(animal -> animal instanceof ChewingGrass)
                .map(animal -> (ChewingGrass) animal)
                .findFirst();
        if (prey.isPresent()) {
            Random random = new Random();
            if (random.nextInt(100) < 70){
                prey.get().die();
                lifePower += 10;
            }else move();
        }
    }




}
