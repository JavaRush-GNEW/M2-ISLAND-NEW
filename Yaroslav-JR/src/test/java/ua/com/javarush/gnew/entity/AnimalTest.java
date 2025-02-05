package ua.com.javarush.gnew.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Island island;
    private Cell startCell;
    private Cell targetCell;
    private Animal animal;

    @BeforeEach
    void setUp() {

        island = new Island(10, 10);
        Cell[][] field = island.getField();
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                field[i][j] = new Cell(new ConcurrentHashMap<>());
            }
        }


        startCell = field[5][5];
        animal = new Wolf();
        startCell.add(animal);
    }

    @Test
    void testMoveWithinBounds() {
        animal.move(startCell, island, 5, 5);

        assertFalse(startCell.getResidents().get(Wolf.class).contains(animal));

        boolean found = false;
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                if (island.getField()[i][j].getResidents().get(Wolf.class) != null &&
                        island.getField()[i][j].getResidents().get(Wolf.class).contains(animal)) {
                    found = true;
                }
            }
        }
        assertTrue(found, "Animal should be present in a new cell after moving.");
    }

    @Test
    void testMoveBoundaryConditions() {
        Cell boundaryCell = island.getField()[0][0];
        boundaryCell.add(animal);
        animal.move(boundaryCell, island, 0, 0);

        boolean outOfBounds = false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x = Math.max(0, Math.min(i, island.getWidth() - 1));
                int y = Math.max(0, Math.min(j, island.getHeight() - 1));
                if (x < 0 || x >= island.getWidth() || y < 0 || y >= island.getHeight()) {
                    outOfBounds = true;
                }
            }
        }
        assertFalse(outOfBounds, "Animal should not move outside the island boundaries.");
    }

    @Test
    void testMoveRemovesFromOldCell() {
        animal.move(startCell, island, 5, 5);

        assertFalse(startCell.getResidents().get(Wolf.class).contains(animal), "Animal should be removed from the old cell after moving.");
    }
}