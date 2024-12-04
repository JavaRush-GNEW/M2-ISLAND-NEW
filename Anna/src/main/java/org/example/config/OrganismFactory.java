package org.example.config;

import org.example.model.map.Cell;
import org.example.model.organism.Organism;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class OrganismFactory {
    private static final String PATH_TO_ORGANISMS = "org.example.model.organism";

    private static Set<Class<? extends Organism>> getSubClasses() {
        Reflections reflections = new Reflections(PATH_TO_ORGANISMS);

        Set<Class<? extends Organism>> classes = reflections.getSubTypesOf(Organism.class);
        return classes.stream()
                .filter(item -> !Modifier.isAbstract(item.getModifiers()))
                .collect(Collectors.toSet());
    }

    public static HashMap<Class<? extends Organism>, Set<Organism>> createResidents(Cell cell) {
        Set<Class<? extends Organism>> organismClasses = getSubClasses();
        HashMap<Class<? extends Organism>, Set<Organism>> instances = new HashMap<>();
        for (Class<? extends Organism> organismClass : organismClasses) {
            try {
                Constructor<?> constructor = organismClass.getDeclaredConstructor();
                Field maxQuantityAtCell = organismClass.getDeclaredField("MAX_QUANTITY_AT_CELL");
                maxQuantityAtCell.setAccessible(true);
                int maxQuantity = maxQuantityAtCell.getInt(null);
                int count = ThreadLocalRandom.current().nextInt(2, maxQuantity / 2 + 1);

                Set<Organism> organisms = new HashSet<>();

                for (int i = 0; i < count; i++) {
                    Organism organism = (Organism) constructor.newInstance();
                    organism.setCell(cell);
                    organisms.add(organism);
                }

                instances.put(organismClass, organisms);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instances;
    }
}
