package ua.com.javarush.gnew.AnimalsTasks;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import java.util.concurrent.Callable;

public class AnimalTask implements Callable<Void> {
    private final Animal animal;
    private final Cell[][] cells;
    private Field field;
    private final int x;
    private final int y;

    // Конструктор
    public AnimalTask(Animal animal, Cell[][] cells, int x, int y) {
        this.animal = animal;
        this.cells = cells;
        this.x = x;
        this.y = y;
    }

    @Override
    public Void call() throws Exception {
        if (animal != null && animal.isAlive()) {
            // Выполнение действий животного в порядке: движение, еда, размножение, смерть
            animal.move(cells, x, y);
            animal.eat(cells, x, y);
            animal.reproduce(field);
            animal.death();  // Проверка на смерть животного

            // Суть задачи - выполнить одно действие за один цикл
        }
        return null;
    }
}


