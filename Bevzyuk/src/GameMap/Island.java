package GameMap;

import Organism.Animal.Animal;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    private final Cell[][] grid;

    public Cell[][] getGrid() {
        return grid;
    }

    public Island(int rows, int cols) {
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j, 200);
            }
        }
    }

    public Cell getRandomCell() {
        int row = ThreadLocalRandom.current().nextInt(0, grid.length);
        int col = ThreadLocalRandom.current().nextInt(0, grid[0].length);
        return grid[row][col];
    }

    public List<Cell> getNeighboringCells(Cell current, int speed) {
        List<Cell> neighbors = new ArrayList<>();
        Set<Cell> visited = new HashSet<>();
        explorePaths(neighbors, visited, current.getRow(), current.getCol(), speed);
        return neighbors;
    }

    private void explorePaths(List<Cell> neighbors, Set<Cell> visited, int row, int col, int remainingSpeed) {
        if (remainingSpeed <= 0 || !isValidCell(row, col)) {
            return;
        }

        Cell cell = grid[row][col];
        if (!visited.contains(cell)) {
            visited.add(cell);
            neighbors.add(cell);
        } else {
            return;
        }

        explorePaths(neighbors, visited,row - 1, col, remainingSpeed - 1);
        explorePaths(neighbors, visited,row + 1, col, remainingSpeed - 1);
        explorePaths(neighbors, visited,row, col - 1, remainingSpeed - 1);
        explorePaths(neighbors, visited,row, col + 1, remainingSpeed - 1);
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
    public boolean hasLivingAnimals() {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (!cell.getAnimals().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
    public void printAnimalStatistics() {
        Map<String, Integer> animalCounts = new HashMap<>();

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                for (Animal animal : cell.getAnimals()) {
                    String type = animal.getClass().getSimpleName();
                    animalCounts.put(type, animalCounts.getOrDefault(type, 0) + 1);
                }
            }
        }

        System.out.println("Статистика тварин на острові:");
        animalCounts.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}
