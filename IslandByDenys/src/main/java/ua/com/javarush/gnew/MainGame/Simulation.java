package ua.com.javarush.gnew.MainGame;



import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Plant.Plant;
import ua.com.javarush.gnew.Predator.Bear;
import ua.com.javarush.gnew.Predator.Fox;
import ua.com.javarush.gnew.Predator.Wolf;
import ua.com.javarush.gnew.herbivore.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final Field field;
    private volatile boolean running = true;

    public Simulation(Field field) {
        this.field = field;
    }

    public void start() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.scheduleAtFixedRate(() -> {
            if (!running) {
                executor.shutdown();
                return;
            }
            publishStatistics();
        }, 0, 3, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            if (!running) {
                executor.shutdown();
                return;
            }
            performActions();
        }, 1, 3, TimeUnit.SECONDS);
    }

    public void stop() {
        running = false;
    }

    private void performActions() {
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                Animal animal = field.getCell(i, j).getAnimal();
                if (animal != null && animal.isAlive()) {
                    animal.move(field.getCells(), i, j);
                    animal.eat(field.getCells(), i, j);
                    animal.reproduce(field);
                    animal.death();
                }
            }
        }
    }

    private void publishStatistics() {
        int wolfCount = 0;
        int rabbitCount = 0;
        int plantCount = 0;
        int sheepCount = 0;
        int horseCount = 0;
        int deerCount = 0;
        int boarCount = 0;
        int foxCount = 0;
        int bearCount = 0;


        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                Animal animal = field.getCell(i, j).getAnimal();
                if (animal instanceof Wolf && animal.isAlive()) {
                    wolfCount++;
                } else if (animal instanceof Rabbit && animal.isAlive()) {
                    rabbitCount++;
                } else if (animal instanceof Sheep && animal.isAlive()) {
                    sheepCount++;
                }else if (animal instanceof Horse && animal.isAlive()) {
                    horseCount++;
                } else if (animal instanceof Deer && animal.isAlive()) {
                    deerCount++;
                }else if (animal instanceof Boar && animal.isAlive()) {
                    boarCount++;
                }else if (animal instanceof Fox && animal.isAlive()) {
                    foxCount++;
                }else if (animal instanceof Bear && animal.isAlive()) {
                    bearCount++;
                }

                Plant plant = field.getCell(i, j).getPlant();
                if (plant != null) {
                    plantCount++;
                }
            }
        }

        System.out.println("Статистика:");
        System.out.println("Волки: " + wolfCount);
        System.out.println("Кролики: " + rabbitCount);
        System.out.println("Овцы: " + sheepCount);
        System.out.println("Кони: " + horseCount);
        System.out.println("Олени: " + deerCount);
        System.out.println("Кабаны: " + boarCount);
        System.out.println("Лисицы: " + foxCount);
        System.out.println("Медведи: " + bearCount);
        System.out.println("Растения: " + plantCount);
    }
}



