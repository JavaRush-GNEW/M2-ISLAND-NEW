package org.example;

import org.example.config.ApplicationContext;
import org.example.config.ApplicationLoader;
import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.animal.herbivore.Horse;
import org.example.model.organism.animal.predator.Wolf;

public class Main {
    public static void main(String[] args) {

        ApplicationLoader loader = ApplicationLoader.getInstance();
        ApplicationContext context = loader.init();

        GameField gameField = context.getGameField();

        Wolf wolf = new Wolf();
        Horse horse = new Horse();
        System.out.println("Island");

        Cell[][] cells = gameField.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                System.out.println("Cell [" + i + " " + j + "]");
                System.out.println(cells[i][j]);
            }
        }

    }

}