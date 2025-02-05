package org.example.model.map;

import org.example.model.organism.Organism;

public class GameField {
    private int width;
    private int height;
    private Cell[][] cells;

    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean add(Organism organism, int x, int y) {
        return cells[x][y].add(organism);
    }
}
