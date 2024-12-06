package game.entity.island;

import game.entity.GameProperty;
import game.entity.Organism;
import game.entity.animal.Animal;
import game.entity.plant.Plant;
import game.utils.GamePropertyUtil;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Island {

    public static final GameProperty GAME_PROPERTY;
    public static final String GAME_PROPERTY_XML = "game_property.xml";

    static {
        try {
            GAME_PROPERTY = GamePropertyUtil.readGameProp(GAME_PROPERTY_XML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final List<String> inhabitantsFullNames = GAME_PROPERTY.getInhabitants();
    private static final List<Constructor<?>> inhabitantsConstructor = getInhabitantsConstructor();
    private static final Map<String, Constructor<?>> inhabitantsConstructorMap = getInhabitantsConstructorMap();
    private static final List<Organism> inhabitants = inhabitantsVariety();
    private Map<String, Organism> organismImageTable = new HashMap<>();

    private static int height = GAME_PROPERTY.getAreaHeight();
    private static int width = GAME_PROPERTY.getAreaWidth();
    private static final Area[][] areas = new Area[height][width];
    private final int simulationTime = GAME_PROPERTY.getSimulationTime();

    public Island() {
    }

    public static Area[][] getArea() {
        return areas;
    }

    Map<String, Organism> getOrganismTable() {
        for (Organism o : inhabitants) {
            organismImageTable.put(o.toString(), o);
        }
        return organismImageTable;
    }

    List<String> getInhabitantsSimpleNames() {
        List<String> inhabitantsSimpleNames = new ArrayList<>();
        for (String fullName : inhabitantsFullNames) {
            String[] split = fullName.split("\\.");
            inhabitantsSimpleNames.add(split[split.length - 1]);
        }
        return inhabitantsSimpleNames;
    }

    public void initialPopulation() {
        //readAndCreateInhabitants();
        readAndCreateInhabitants2();
        printStatisticsAsTable();
    }

    private static List<Constructor<?>> getInhabitantsConstructor() {
        List<Constructor<?>> constructors = new ArrayList<>();

        try {
            for (String fullName : inhabitantsFullNames) {
                Class<?> aClass = Class.forName(fullName);
                constructors.add(aClass.getConstructor());
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return constructors;
    }

    private static Map<String, Constructor<?>> getInhabitantsConstructorMap() {
        Map<String, Constructor<?>> constructorsMap = new HashMap<>();

        try {
            for (String fullName : inhabitantsFullNames) {
                Class<?> aClass = Class.forName(fullName);
                String simpleName = aClass.getSimpleName();
                constructorsMap.put(simpleName, aClass.getConstructor());
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return constructorsMap;
    }

    private static List<Organism> inhabitantsVariety() {
        List<Organism> inhabitants = new ArrayList<>();
        try {

            for (Constructor<?> constructor : inhabitantsConstructor) {
                Organism o = (Organism) constructor.newInstance();
                inhabitantsConstructorMap.put(o.toString(), constructor);
                inhabitants.add(o);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return inhabitants;
    }


    private String getInhabitantImage(Organism inhabitant) {
        return inhabitant.getImage();
    }

    private void readAndCreateInhabitants2() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                areas[i][j] = new Area(i, j);
                for (Organism inhabitant : inhabitants) {
                    int maxCellQuantity = inhabitant.getProperties().getMaxCellQuantity();
                    ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                    int nextInt = threadLocalRandom.nextInt(0, maxCellQuantity);
                    HashSet<Animal> animalSet = new HashSet<>();
                    HashSet<Plant> plantSet = new HashSet<>();
                    for (int k = 0; k < nextInt; k++) {
                        String string = inhabitant.toString();
                        Constructor<?> inhabitantConstructor = inhabitantsConstructorMap.get(string);
                        try {
                            inhabitant = (Organism) inhabitantConstructor.newInstance();
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                        if (inhabitant instanceof Animal) {
                            animalSet.add((Animal) inhabitant);
                        } else {
                            plantSet.add((Plant) inhabitant);
                        }
                    }

                    if (inhabitant instanceof Animal) {
                        areas[i][j].getAnimalMap().put(inhabitant.toString(), animalSet);
                    } else {
                        areas[i][j].getPlantMap().put(inhabitant.toString(), plantSet);
                    }
                }
            }
        }
    }

    private void readAndCreateInhabitants() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                areas[i][j] = new Area(j, i);
                for (String name : inhabitantsFullNames) {
                    try {
                        Class<?> aClass = Class.forName(name);
                        System.out.println("simple name :" + aClass.getSimpleName());
                        Constructor<?> inhabitantConstructor = aClass.getConstructor();
                        Organism o = (Organism) inhabitantConstructor.newInstance();

                        int maxCellQuantity = o.getProperties().getMaxCellQuantity();
                        System.out.println("Inhabitant name:" + name + " max cell quantity:" + maxCellQuantity);

                        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                        int nextInt = threadLocalRandom.nextInt(0, maxCellQuantity);
                        System.out.println("nextInt:" + nextInt);

                        Organism organismInstance;
                        HashSet<Animal> animalSet = new HashSet<>();
                        HashSet<Plant> plantSet = new HashSet<>();
                        for (int k = 0; k < nextInt; k++) {
                            organismInstance = (Organism) inhabitantConstructor.newInstance();
                            if (organismInstance instanceof Animal) {
                                animalSet.add((Animal) organismInstance);
                            } else {
                                plantSet.add((Plant) organismInstance);
                            }
                        }
                        areas[i][j].getAnimalMap().put(o.toString(), animalSet);
                        areas[i][j].getPlantMap().put(o.toString(), plantSet);


                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                             IllegalAccessException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void printStatisticsAsTable() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                printAreaAnimals(i, j);
                printAreaPlants(i, j);
            }
            System.out.println();
        }
    }

    private void printAreaAnimals(int i, int j) {
        Map<String, Set<Animal>> animalMap = areas[i][j].getAnimalMap();
        for (String name : getInhabitantsSimpleNames()) {

            if (animalMap.containsKey(name)) {
                Set<Animal> animals = animalMap.get(name);
                String image = getInhabitantImage(getOrganismTable().get(name));
                //String image = getOrganismTable().get(name).getImage();
                System.out.printf("%s:%4s %s", image, animals.size(), "");
            }
        }
        System.out.print("|");
    }

    private void printAreaPlants(int i, int j) {
        Map<String, Set<Plant>> plantMap = areas[i][j].getPlantMap();
        for (String name : getInhabitantsSimpleNames()) {

            if (plantMap.containsKey(name)) {
                Set<Plant> plants = plantMap.get(name);
                String image = getInhabitantImage(getOrganismTable().get(name));
                //String image = getOrganismTable().get(name).getImage();
                System.out.printf("%s:%4s %s", image, plants.size(), "");
            }
        }
        System.out.print("||");
    }

    public void feedingOnIsland() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Area area = areas[i][j];

                Map<String, Set<Animal>> animalMap = area.getAnimalMap();

                Iterator<Map.Entry<String, Set<Animal>>> iterator = animalMap.entrySet().iterator();

                while (iterator.hasNext()) {

                    Map.Entry<String, Set<Animal>> entry = iterator.next();
                    System.out.println(entry);
                    Set<Animal> animals = entry.getValue();

                    if (!animals.isEmpty()) {
                        Iterator<Animal> animalIterator = animals.iterator();
                        while (animalIterator.hasNext()) {
                            Animal animal = animalIterator.next();
                            System.out.println("Animal:" + animal);
                            animal.eat(area);
                        }
                    }
                }

            }
        }
    }

    public void reproducingOnIsland() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Area area = areas[i][j];
                Set<Animal> youngAnimals;
                Map<String, Set<Animal>> animalMap = area.getAnimalMap();

                Iterator<Map.Entry<String, Set<Animal>>> iterator = animalMap.entrySet().iterator();

                while (iterator.hasNext()) {

                    Map.Entry<String, Set<Animal>> entry = iterator.next();
                    System.out.println(entry);
                    Set<Animal> animals = entry.getValue();
                    youngAnimals = new HashSet<>();

                    if (!animals.isEmpty()) {
                        Iterator<Animal> animalIterator = animals.iterator();
                        while (animalIterator.hasNext()) {
                            Animal animal = animalIterator.next();
                            System.out.println("Animal:" + animal);
                            Animal youngAnimal;
                            if ((youngAnimal = (Animal) animal.reproduce()) != null) {

                                youngAnimals.add(youngAnimal);
                                System.out.println(animal );
                            }
                        }
                    }
                    animals.addAll(youngAnimals);
                    entry.setValue(animals);
                }

            }
        }
    }

    public void simulateLivingOnIsland() {
        initialPopulation();
        feedingOnIsland(); //phase 1
        printStatisticsAsTable();
        reproducingOnIsland(); //phase 2
        System.out.println("-".repeat(200));
        printStatisticsAsTable();
    }

}
