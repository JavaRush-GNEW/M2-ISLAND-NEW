package org.example.entity.map;


public class Island {
    private final Cell[][] grid;

    public Island(int width, int height) {
        grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell();
            }
        }
        IslandGenerator islandGenerator = new IslandGenerator();
        islandGenerator.populateIsland(grid,5);
    }


    public Cell[][] getGrid() {
        return grid;
    }
}

