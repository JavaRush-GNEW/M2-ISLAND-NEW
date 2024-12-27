package org.ua.com.javarush.gnew.config;

import lombok.Getter;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;


public class Statistics {
    private static Statistics INSTANCE;
    private final Map<Class<? extends Organism>, Integer> populationMap ;

    private Statistics(){
        populationMap = new HashMap<>();
    }

    public static Statistics getINSTANCE(){
        if (INSTANCE == null) {
            INSTANCE = new Statistics();
        }
        return INSTANCE;
    }

    public void reset(){
        populationMap.clear();
    }

    public void addCount(Class<? extends Organism> clazz, int count) {
        populationMap.merge(clazz, count, Integer::sum);
    }

    public void print() {
        System.out.println("=== Универсальная статистика по видам ===");
        if (populationMap.isEmpty()) {
            System.out.println("На острове нет животных.");
        } else {
            for (Map.Entry<Class<? extends Organism>, Integer> entry : populationMap.entrySet()) {
                System.out.println("Вид [" + entry.getKey().getSimpleName() + "]: " + entry.getValue());
            }
        }
        System.out.println("=========================================");
    }


}
