package org.example;



import org.example.entity.map.Cell;
import org.example.entity.map.Island;

import static org.example.entity.animal.Animal.moveAllAnimals;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        Island island = new Island(width, height);

        System.out.println("Початковий стан острова:");
        printIsland(island.getGrid());

        // Переміщення тварин
        moveAllAnimals(island.getGrid());

        // Вивід стану після переміщення
        System.out.println("\nСтан острова після переміщення:");
        printIsland(island.getGrid());


        System.out.println("_________________________");

    }
    private static void printIsland(Cell[][] island) {
        for (Cell[] row : island) {
            for (Cell cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}