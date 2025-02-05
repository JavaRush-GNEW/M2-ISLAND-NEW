package context;

import entities.Animal;
import entities.herbivores.*;
import entities.predators.*;
import map.GameMap;
import map.IslandMap;
import utils.JSONLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ApplicationLoader {

    private HashMap<Class<?extends Animal>,Integer> animalsPerCeil;


    public ApplicationContext init(){
        loadProperties("animal_properties.json");
        loadAnimals();
        ApplicationContext context = ApplicationContext.getInstance();
        GameMap gameMap = new IslandMap(5,5,context);
        context.setGAME_MAP(gameMap);
        context.setAnimalsPerCeil(animalsPerCeil);
        context.setTHREADS_LIMIT(10);

        return context;

    }

    private void loadProperties(String path){
        try {
            JSONLoader.load(path);
            // Запуск гри
        } catch (IOException e) {
            System.err.println("Failed to load animal properties: " + e.getMessage());
        }

    }

    public void loadAnimals(){
        animalsPerCeil = new HashMap<>();
        animalsPerCeil.put(Wolf.class,Wolf.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Rabbit.class,Rabbit.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Snake.class,Snake.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Fox.class,Fox.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Bear.class,Bear.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Eagle.class,Eagle.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Horse.class,Horse.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Boar.class,Boar.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Buffalo.class,Buffalo.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Deer.class,Deer.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Duck.class,Duck.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Goat.class,Goat.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Mouse.class,Mouse.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Sheep.class,Sheep.ANIMAL_PER_CEIL);
        animalsPerCeil.put(Caterpillar.class,Caterpillar.ANIMAL_PER_CEIL);



    }
}
