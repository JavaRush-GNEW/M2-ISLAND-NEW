package org.ua.com.javarush.gnew;


import org.ua.com.javarush.gnew.Threads.ThreadManager;


public class Main {
    public static void main(String[] args) {
        ThreadManager.getInstance().startGame();
        ThreadManager.getInstance().startCollectStatistics();


    }
}
