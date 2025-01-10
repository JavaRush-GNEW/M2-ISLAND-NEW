package game.service;

import game.entity.island.Island;
import java.util.concurrent.Phaser;

public class LivingOnIslandThread implements Runnable{

    Phaser phaser;
    String threadName;
    int width;
    int height;
    Island island;

    public LivingOnIslandThread(Phaser phaser, Island island, int width, int height, String name) {
        this.phaser = phaser;
        this.width = width;
        this.height = height;
        this.threadName = name;
    }
    @Override
    public void run() {

        System.out.println("Потік" + threadName + "починає фазу ХАРЧУВАННЯ");
        island.feedingOnIsland(width, height);
        phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Потік" + threadName + "починає фазу ПЕРЕМІЩЕННЯ");
        //TODO: add moovingOnIsland() method
        //island.moovingOnIsland();
        phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Потік" + threadName + "починає фазу РОЗМНОЖЕННЯ");
        island.reproducingOnIsland(width, height);
        phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
