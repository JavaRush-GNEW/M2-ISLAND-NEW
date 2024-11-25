package org.example.entity.map;


public class Island {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[y][x];
        }
        throw new IndexOutOfBoundsException("Неправильні координати: x=" + x + ", y=" + y);
    }

    public void displayIsland() {
        System.out.println("Острів (ширина: " + width + ", висота: " + height + ")");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("[" + grid[i][j].getHerbivores().size() + "] "); // Кількість тварин у клітині
            }
            System.out.println();
        }
    }

}
