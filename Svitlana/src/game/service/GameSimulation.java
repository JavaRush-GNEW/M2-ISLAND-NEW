package game.service;

import game.entity.GameProperty;
import game.entity.island.Island;
import game.utils.GamePropertyUtil;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ScheduledExecutorService;

public class GameSimulation {
    public static final GameProperty GAME_PROPERTY;
    public static final String GAME_PROPERTY_XML = "game_property.xml";

    static {
        try {
            GAME_PROPERTY = GamePropertyUtil.readGameProp(GAME_PROPERTY_XML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int islandHeight = GAME_PROPERTY.getAreaHeight();
    public static int islandWidth = GAME_PROPERTY.getAreaWidth();

    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);
        int curPhase;
        Island island = new Island();

        System.out.println("Початок симуляції життя на острові");
        island.initialPopulation(islandHeight, islandWidth);
        System.out.println("Фаза ПОЧАТКОВА ІНІЦІАЛІЗАЦІЯ завершена");

//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
//
//        executor.execute(new LivingOnIslandThread(phaser, island, 0,islandHeight/2, 0, islandWidth/2, "First thread"));
//        executor.execute(new LivingOnIslandThread(phaser, island, width, height, "Second thread"));
//
//        curPhase = phaser.arriveAndAwaitAdvance();
//        System.out.println("Фаза ХАРЧУВАННЯ " + curPhase + "завершена");
//
//        curPhase = phaser.arriveAndAwaitAdvance();
//        System.out.println("Фаза ПЕРЕМІЩЕННЯ " + curPhase + "завершена");
//
//        curPhase = phaser.arriveAndAwaitAdvance();
//        System.out.println("Фаза РОЗМНОЖЕННЯ " + curPhase + "завершена");

        phaser.arriveAndDeregister();

        if(phaser.isTerminated()) {
            System.out.println("Синхронізатор фаз завершив роботу");
        }
    }
}
