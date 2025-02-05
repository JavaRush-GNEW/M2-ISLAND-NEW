package ua.com.javarush.gnew.config;

import ua.com.javarush.gnew.model.map.Cell;
import ua.com.javarush.gnew.model.map.GameField;
import ua.com.javarush.gnew.model.organism.Organism;

import java.util.HashMap;
import java.util.Set;

public class ApplicationLoader {
    private static ApplicationLoader INSTANCE;


    ApplicationContext applicationContext = ApplicationContext.getInstance();

    private ApplicationLoader() {
    }

    public static ApplicationLoader getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ApplicationLoader();
        }

        return INSTANCE;
    }

    public ApplicationContext init(){
        initGameField(5,5);

        return applicationContext;
    }


    public void initGameField(int width, int height){
        GameField gameField = new GameField(width, height);

        Cell[][] cells = gameField.getCells();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                HashMap<Class<? extends Organism>, Set<Organism>> residents = new HashMap<>();
                cells[i][j] = new Cell(residents);
            }
        }

        this.applicationContext.setGameField(gameField);
    }
}
