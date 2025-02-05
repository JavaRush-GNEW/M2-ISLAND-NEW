package org.example.config;

import org.example.model.map.GameField;

public class ApplicationContext {
    private static ApplicationContext INSTANCE;
    private GameField gameField;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationContext();
        }
        return INSTANCE;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }
}
