package org.example;

import org.example.config.ApplicationContext;
import org.example.config.ApplicationLoader;
import org.example.model.map.GameField;

public class Main {
    public static void main(String[] args) {

        ApplicationLoader loader = ApplicationLoader.getInstance();
        ApplicationContext context = loader.init();

        GameField gameField = context.getGameField();

        System.out.println("Island");

    }
}