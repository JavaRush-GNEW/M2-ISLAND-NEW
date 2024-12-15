package ua.com.javarush.gnew.Field;


import lombok.Getter;
import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void performAction() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                Animal animal = cell.getAnimal();

                if (animal != null && animal.isAlive()) {
                    animal.move(cells, i, j);
                    animal.eat(cells, i, j);
                    animal.reproduce(this);
                    animal.death();
                }

                Plant plant = cell.getPlant();
                if (plant != null) {
                    if (animal != null && animal.isAlive() && animal.eatPlant(plant)) {
                        cell.setPlant(null);
                    }
                }
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


