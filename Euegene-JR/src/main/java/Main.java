import context.ApplicationContext;
import context.ApplicationLoader;
import map.GameMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        ApplicationLoader applicationLoader = new ApplicationLoader();
        ApplicationContext context = applicationLoader.init();
        GameMap gameMap = context.getGAME_MAP();
        try {
            gameMap.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}