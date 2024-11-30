package org.example.entity.map;
import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.Horse;
import org.example.entity.animal.predator.Wolf;

import java.util.Random;

public class IslandGenerator {

    private final Random random = new Random();

    protected void populateIsland(Cell[][] island, int maxEntitiesPerCell) {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                // Створення випадкової кількості тварин
                int numEntities = random.nextInt(maxEntitiesPerCell + 1); // Від 0 до maxEntitiesPerCell

                for (int k = 0; k < numEntities; k++) {
                    Animal entity = createRandomAnimal();
                    island[i][j].addEntity(entity);
                }
            }
        }
    }

    private Animal createRandomAnimal() {
        // Випадковий вибір типу тварини
        int type = random.nextInt(2); // 0 - Herbivore, 1 - Predator
        return switch (type) {
            case 0 -> new Wolf(); // Для тесту можна додати ще типи тварин
            case 1 -> new Horse();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
