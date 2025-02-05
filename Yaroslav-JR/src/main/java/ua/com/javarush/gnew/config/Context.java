package ua.com.javarush.gnew.config;

import ua.com.javarush.gnew.entity.island.Island;

public class Context {
    private static Context INSTANCE;
    private Island island;

    private Context() {
    }

    public static synchronized Context getINSTANCE() {
            if (INSTANCE == null){
                INSTANCE = new Context();
            }
        return INSTANCE;
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }
}
