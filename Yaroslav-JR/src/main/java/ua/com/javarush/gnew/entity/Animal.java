package ua.com.javarush.gnew.entity;

import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;

import java.util.Random;

public abstract class Animal extends Organism {



    public boolean isSatiated = false;

    public Animal(int maxCellResidents, double initialWeight) {
        super(maxCellResidents, initialWeight);
    }


    public boolean isSatiated() {
        return isSatiated;
    }

    public void reproduce(Cell currentCell) {
        this.checkSatiation();
        if (!isSatiated) {
            try {
                Organism offspring = this.getClass().getDeclaredConstructor().newInstance();
                synchronized (currentCell) {
                    currentCell.add(offspring);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
