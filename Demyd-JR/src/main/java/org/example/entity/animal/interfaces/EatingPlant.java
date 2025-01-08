package org.example.entity.animal.interfaces;

import org.example.entity.map.Cell;
import org.example.entity.plant.Plant;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public interface EatingPlant {
    Random RANDOM = new Random();

    default void eatPlant(Cell currentCell) {
        List<Plant> plants = currentCell.getPlants().stream()
                .filter(entity -> entity instanceof Plant)
                .map(entity -> (Plant) entity)
                .collect(Collectors.toList());

        if (!plants.isEmpty()) {
            // Random plant
            Plant plant = plants.get(RANDOM.nextInt(plants.size()));
            currentCell.getPlants().remove(plant);
//            System.out.println(getClass().getSimpleName() + " з'їв " + plant.getNamePlant());
        } else {
//            System.out.println(getClass().getSimpleName() + " не знайшов рослин у клітинці.");
        }
    }
}

