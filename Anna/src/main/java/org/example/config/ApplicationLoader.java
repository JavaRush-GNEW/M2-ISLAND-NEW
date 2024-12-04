package org.example.config;

import org.example.model.map.Cell;
import org.example.model.map.GameField;
import org.example.model.organism.Organism;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ApplicationLoader {
    private static final int GAME_FIELD_WIDTH = 5;
    private static final int GAME_FIELD_HEIGHT = 5;
    private static ApplicationLoader INSTANCE;

    private ApplicationLoader() {
    }

    public static ApplicationLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationLoader();
        }
        return INSTANCE;
    }

    ApplicationContext applicationContext = ApplicationContext.getInstance();

    public ApplicationContext init() {
        initGameField(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        return applicationContext;
    }

    public void initGameField(int width, int height) {
        GameField gameField = new GameField(width, height);
        Cell[][] cells = gameField.getCells();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(new HashMap<>());
                cells[i][j] = cell;
                HashMap<Class<? extends Organism>, Set<Organism>> residents = OrganismFactory.createResidents(cell);
                cell.setResidents(residents);
            }
        }
        System.out.println(cells[0][0]);
        this.applicationContext.setGameField(gameField);

        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                Set<Map.Entry<Class<? extends Organism>, Set<Organism>>> entries = cell1.getResidents().entrySet();
                Iterator<Map.Entry<Class<? extends Organism>, Set<Organism>>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Class<? extends Organism>, Set<Organism>> next = iterator.next();
                    Set<Organism> organisms = next.getValue();
                    organisms.iterator().next();
                    organisms.iterator().next();
                    organisms.iterator().next().lifeCycle();
//                    organisms.iterator().next().lifeCycle();
//                    organisms.iterator().next().lifeCycle();
                }
            }
        }
        System.out.println(cells[0][0]);
    }

}
