package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.plant.Grass;

public class Sheep extends ChewingGrass {
    private static final int readyForReproduce = 80;


    public void reproduce(Cell cell){
        if(getLifePower() >= readyForReproduce){
            boolean hasPartner = cell.getAnimals().stream().anyMatch(animal -> animal instanceof Sheep && animal != this);
            if (hasPartner){
                cell.addAnimal(new Sheep());
            }else move();
        }
    }
    public void eat(Cell cell) {
        Grass grass = cell.getGrass().stream().findFirst().orElse(null);
        if (grass != null){
            grass.die();
            lifePower += 10;
        }else move();
    }

}
