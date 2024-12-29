package org.ua.com.javarush.gnew.Config;


import org.reflections.Reflections;
import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnimalCollection {
    private final Reflections reflections = new Reflections("org.ua.com.javarush.gnew.model.Animals");

    private final Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Animal.class);
    private static AnimalCollection INSTANCE;
    private final List<Class<? extends Organism>> animals;

    private AnimalCollection(){
        this.animals = new ArrayList<>();
    }

    public static AnimalCollection getInstane() {
        if (INSTANCE == null) {
            INSTANCE = new AnimalCollection();
        }
        return INSTANCE;
    }

    public List<Class<? extends Organism>> getAnimals() {
        for (Class<?> clazz: classSet) {
            if (Organism.class.isAssignableFrom(clazz)) {
                animals.add(clazz.asSubclass(Organism.class));
            }
            System.out.println(animals);
        }
        return animals;
    }


}
