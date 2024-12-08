package context;

import entities.Animal;
import entities.herbivores.Rabbit;
import entities.predators.Wolf;
import map.GameMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ApplicationContext {

    private int THREADS_LIMIT;
    private HashMap<Class<? extends Animal>,Integer> animalsPerCeil;
    private GameMap GAME_MAP;
    private static ApplicationContext INSTANCE;


    private ApplicationContext() {
    }

    public static ApplicationContext getInstance(){
        if(INSTANCE==null){
            INSTANCE = new ApplicationContext();
        }
        return INSTANCE;
    }

    public HashMap<Class<? extends Animal>, Integer> getAnimalsPerCeil() {
        return animalsPerCeil;
    }

    public void setAnimalsPerCeil(HashMap<Class<? extends Animal>, Integer> animalsPerCeil) {
        this.animalsPerCeil = animalsPerCeil;
    }

    public GameMap getGAME_MAP() {
        return GAME_MAP;
    }

    public void setGAME_MAP(GameMap GAME_MAP) {
        this.GAME_MAP = GAME_MAP;
    }

    public int getTHREADS_LIMIT() {
        return THREADS_LIMIT;
    }

    public void setTHREADS_LIMIT(int THREADS_LIMIT) {
        this.THREADS_LIMIT = THREADS_LIMIT;
    }
}
