package ua.com.javarush.gnew.Field;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Field {
    private final Cell[][] cells;

    public Field(int rows, int cols) {
        this.cells = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getRandomEmptyCell() {
        List<Cell> emptyCells = new ArrayList<>();

        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cell[j].getAnimal() == null) {
                    emptyCells.add(cell[j]);
                }
            }
        }

        if (!emptyCells.isEmpty()) {
            int randomIndex = (int) (Math.random() * emptyCells.size());
            return emptyCells.get(randomIndex);
        }

        return null;
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
            return cells[x][y];
        }
        return null;
    }
}


