package ua.com.javarush.gnew.entity.meatEaters;

import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;

import java.util.Random;

public class Wolf extends MeatEaters {
    private static final int readyForReproduce = 80;

    @Override
    public void eat(Sheep prey) {
        Random random = new Random();
        if (random.nextInt(100) < 70) {
            lifePower += 10;
            prey.die();
        }
    }
    public void reproduce(Cell cell){
        if(getLifePower() >= readyForReproduce){
            boolean hasPartner = cell.getAnimals().stream().anyMatch(animal -> animal instanceof Wolf && animal != this);
            if (hasPartner){
                setLifePower(50);
                cell.addAnimal(new Wolf());
            }
        }
    }

}
