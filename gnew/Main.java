package ua.com.javarush.gnew;

import ua.com.javarush.gnew.config.ApplicationContext;
import ua.com.javarush.gnew.config.ApplicationLoader;
import ua.com.javarush.gnew.model.map.Cell;
import ua.com.javarush.gnew.model.map.GameField;
import ua.com.javarush.gnew.model.organism.Organism;
import ua.com.javarush.gnew.model.organism.animal.herbivore.Horse;
import ua.com.javarush.gnew.model.organism.animal.predator.Wolf;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ApplicationLoader loader = ApplicationLoader.getInstance();
        ApplicationContext context = loader.init();
        GameField gameField = context.getGameField();


        Wolf wolf = new Wolf();
        Horse horse = new Horse();

        gameField.add(wolf, 1, 1);
        gameField.add(horse, 0, 0);

        Cell[][] cells = gameField.getCells();


        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                System.out.println("Cell [" + i  + " " + j + "]");

                System.out.println(cells[i][j]);
            }
        }

    }

}