package ua.com.javarush.gnew.entity.island;

import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Random;

public class Island {

    private int width;
    private int height;
    private Cell[][] field;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.field = new Cell[width][height];
    }

    public Cell[][] getField() {
        return field;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public boolean add(Organism organism, int x, int y){
        return field[x][y].add(organism);
    }
}
