package org.ua.com.javarush.gnew.Island;

import lombok.Data;
import lombok.Getter;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import org.ua.com.javarush.gnew.model.Animals.Predator.Wolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Map {
    private int width;
    private int height;
    private Random random = new Random();

    @Getter
    private Cell[][] cells;



    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
    }

    public void mapInit(List<Organism> animal) {


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();

                Organism randomanimal = animal.get(random.nextInt(animal.size()));

                if (randomanimal instanceof Wolf){
                    cells[i][j].getResidents().add(new Wolf());
                } else if (randomanimal instanceof Horse) {
                    cells[i][j].getResidents().add(new Horse());
                }

            }
        }
    }



}
