package game.entity.island;

import game.entity.GameProperty;
import game.entity.Organism;
import game.entity.animal.Animal;
import game.entity.plant.Plant;
import game.utils.GamePropertyUtil;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Island {

    public static final GameProperty GAME_PROPERTY;
    public static final String GAME_PROPERTY_XML = "game_property.xml";

    private int height = GAME_PROPERTY.getAreaHeight();
    private int width = GAME_PROPERTY.getAreaWidth();
    private final Area[][] areas = new Area[height][width];
    private final int simulationTime = GAME_PROPERTY.getSimulationTime();
    private final List<String> inhabitantsNames = GAME_PROPERTY.getInhabitants();

    static {
        try {
            GAME_PROPERTY = GamePropertyUtil.readGameProp(GAME_PROPERTY_XML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Island() {
    }


    public void initialPopulation() {
        readAndCreateInhabitants();
        printStatistics();
    }

    private void readAndCreateInhabitants() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                areas[i][j] = new Area();
                for (String name : inhabitantsNames) {
                    try {
                        Class<?> aClass = Class.forName(name);
                        System.out.println("simple name :" + aClass.getSimpleName());
                        Constructor<?> inhabitantConstructor = aClass.getConstructor();
                        Organism o = (Organism) inhabitantConstructor.newInstance();

                        int maxCellQuantity = o.getProperties().getMaxCellQuantity();
                        System.out.println("Inhabitant name:" + name + " max cell quantity:" + maxCellQuantity);

                        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                        int nextInt = threadLocalRandom.nextInt(0, maxCellQuantity);

                        for (int k = 0; k < nextInt; k++) {
                            if (inhabitantConstructor.newInstance() instanceof Animal) {
                                areas[i][j].getAnimalSet().add((Animal) inhabitantConstructor.newInstance());
                            } else areas[i][j].getPlantSet().add((Plant) inhabitantConstructor.newInstance());
                        }

                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                             IllegalAccessException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void printStatistics() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.println(areas[i][j].getAnimalSet());
                System.out.println(areas[i][j].getPlantSet());
                System.out.println("-----------------------------------");
            }
        }
    }

    public void simulateLivingOnIsland() {

    }
}
