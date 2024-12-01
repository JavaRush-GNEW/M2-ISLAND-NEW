package org.example.entity.animal.interfaces;

import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.map.Cell;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public interface Hunting {
    Random RANDOM = new Random();

    Map<Class<? extends Animal>, Integer> getHuntingChances();
    default void hunt(Cell currentCell){
        // List //
        List<Herbivore> herbivores = currentCell.getEntities().stream()
                .filter(entity -> entity instanceof Herbivore)
                .map(entity -> (Herbivore) entity)
                .filter(pres -> getHuntingChances().containsKey(pres.getClass()))
                .collect(Collectors.toList());

        if (!herbivores.isEmpty()) {
            // Random animal to eat //
            Herbivore prey = herbivores.get(RANDOM.nextInt(herbivores.size()));

            // Chance eat this animal //
            int huntingChance = getHuntingChances().getOrDefault(prey.getClass(), 0);

            if (RANDOM.nextInt(100) <= huntingChance) {
                currentCell.getEntities().remove(prey);
//                System.out.println(getClass().getSimpleName() + " вполював " + prey.getClass().getSimpleName());
            } else {
//                System.out.println(getClass().getSimpleName() + " не зміг вполювати " + prey.getClass().getSimpleName());
            }
        } else {
//            System.out.println(getClass().getSimpleName() + " не знайшов жертви у клітинці.");
        }
    };

}
