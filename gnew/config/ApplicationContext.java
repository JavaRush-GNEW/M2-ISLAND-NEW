package ua.com.javarush.gnew.config;

import ua.com.javarush.gnew.model.map.GameField;

public class ApplicationContext {
    private static ApplicationContext INSTANCE;

    private GameField gameField;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ApplicationContext();
        }
        return INSTANCE;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public GameField getGameField() {
        return gameField;
    }
}
