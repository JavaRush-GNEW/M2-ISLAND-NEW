package ua.com.javarush.gnew;

import ua.com.javarush.gnew.config.Context;
import ua.com.javarush.gnew.config.Loader;
import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Loader loader = Loader.getINSTANCE();
        Context context = loader.init();
        Island island = context.getIsland();

        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                System.out.println("Cell [" + i + "][" + j + "] has " +
                        island.getField()[i][j].getResidents().size() + " organisms.");
            }
        }

    }


}