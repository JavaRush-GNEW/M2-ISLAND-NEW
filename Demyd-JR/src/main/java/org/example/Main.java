package org.example;


import org.example.entity.map.Cell;
import org.example.entity.map.Island;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Island island = new Island(5, 5);

        island.displayIsland();

        Cell cell = island.getCell(0, 0);
        System.out.println("Herbivores: " + cell.getHerbivores().size());
        System.out.println("Predators: " + cell.getPredators().size());
        System.out.println("Plants: " + cell.getPlants().size());


    }
}