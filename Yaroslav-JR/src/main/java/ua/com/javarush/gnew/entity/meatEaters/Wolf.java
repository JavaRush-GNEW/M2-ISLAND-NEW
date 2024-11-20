package ua.com.javarush.gnew.entity.meatEaters;

import ua.com.javarush.gnew.Main;
import ua.com.javarush.gnew.entity.chewingGrass.ChewingGrass;
import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;

import java.util.Optional;
import java.util.Random;

public class Wolf extends MeatEaters {
    private static final int readyForReproduce = 80;

    public void reproduce(Cell cell){
        if(getLifePower() >= readyForReproduce){
            boolean hasPartner = cell.getAnimals().stream().anyMatch(animal -> animal instanceof Wolf && animal != this);
            if (hasPartner){
                cell.addAnimal(new Wolf());
            }
        }
    }


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
            }
        }
    }

}
