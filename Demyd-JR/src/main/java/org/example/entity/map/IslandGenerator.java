package org.example.entity.map;

import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.*;
import org.example.entity.animal.predator.*;
import org.example.entity.plant.Grass;
import org.example.entity.plant.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IslandGenerator {

    private final Random RANDOM = new Random();
    private static final Map<Integer, Class<? extends Animal>> ANIMAL_TYPES = new HashMap<>();

    static {
        ANIMAL_TYPES.put(0, Wolf.class);
        ANIMAL_TYPES.put(1, Fox.class);
        ANIMAL_TYPES.put(2, Eagle.class);
        ANIMAL_TYPES.put(3, Bear.class);
        ANIMAL_TYPES.put(4, BoaConstrictor.class);
        ANIMAL_TYPES.put(5, Boar.class);
        ANIMAL_TYPES.put(6, Buffalo.class);
        ANIMAL_TYPES.put(7, Deer.class);
        ANIMAL_TYPES.put(8, Duck.class);
        ANIMAL_TYPES.put(9, Geese.class);
        ANIMAL_TYPES.put(10, Goat.class);
        ANIMAL_TYPES.put(11, Horse.class);
        ANIMAL_TYPES.put(12, Mouse.class);
        ANIMAL_TYPES.put(13, Rabbit.class);
        ANIMAL_TYPES.put(14, Sheep.class);
    }


    // Method add Random Animal //
    protected void populateIsland(Cell[][] island, int maxEntitiesPerCell) {
        // For all cell //
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                // Random per one cell //
                int numEntities = RANDOM.nextInt(maxEntitiesPerCell + 1);

                // For all animal //
                for (int k = 0; k < numEntities; k++) {
                    // Random create animal //
                    Animal entity = createRandomAnimal();
                    island[i][j].addEntity(entity);
                }
                // For all plants //
                for (int a = 0; a < numEntities; a++) {
                    Plant entityPant = new Grass();
                    island[i][j].addPlant(entityPant);
                }
            }
        }
    }


    // Create random animal with map //
    public Animal createRandomAnimal() {

        int type = new Random().nextInt(ANIMAL_TYPES.size());

        // return example type animal //
        try {
            return ANIMAL_TYPES.get(type).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
