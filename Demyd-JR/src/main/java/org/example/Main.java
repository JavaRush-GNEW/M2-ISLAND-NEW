package org.example;

import org.example.entity.animal.herbivore.Horse;
import org.example.entity.animal.predator.Wolf;
import org.example.entity.map.Cell;
import org.example.entity.map.Island;
import org.example.entity.plant.Cactus;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Island island = new Island(5,5);

        island.displayIsland();

    }
}