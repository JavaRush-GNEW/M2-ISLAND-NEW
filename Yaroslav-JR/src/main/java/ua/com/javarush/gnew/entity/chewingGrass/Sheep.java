package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.island.Cell;

public class Sheep extends ChewingGrass {
    private static final int readyForReproduce = 80;
    public Sheep(){

    }
    public void reproduce(Cell cell){
        if(getLifePower() >= readyForReproduce){
            boolean hasPartner = cell.getAnimals().stream().anyMatch(animal -> animal instanceof Sheep && animal != this);
            if (hasPartner){
                cell.addAnimal(new Sheep());
            }else move();
        }
    }

}
