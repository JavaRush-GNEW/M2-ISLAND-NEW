package org.example.entity.animal;

import org.example.entity.LivingEntity;
import org.example.entity.animal.interfaces.Reproduction;
import org.example.entity.map.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Animal extends LivingEntity implements Reproduction {
    private final String ANIMAL_NAME;
    private final int MAX_WEIGHT;
    private final int MAX_ANIMAL_CELL;
    private final int MAX_MOVE_SPEED;
    private final int MAX_SATURATION;

    public Animal(String nameAnimal, int maxWeight, int maxAnimalCell, int maxMove, int maxSaturation) {
        this.ANIMAL_NAME = nameAnimal;
        this.MAX_WEIGHT = maxWeight;
        this.MAX_ANIMAL_CELL = maxAnimalCell;
        this.MAX_MOVE_SPEED = maxMove;
        this.MAX_SATURATION = maxSaturation;
    }


    public int getMAX_MOVE_SPEED() {
        return MAX_MOVE_SPEED;
    }
    public String getANIMAL_NAME() {
        return ANIMAL_NAME;
    }

    // Логіка переміщення для всіх тварин
    public static void moveAllAnimals(Cell[][] island) {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                Cell cell = island[i][j];
                List<Animal> animalsToMove = new ArrayList<>(cell.getEntities()); // Копія списку тварин

                for (Animal animal : animalsToMove) {
                    int steps = new Random().nextInt(animal.getMAX_MOVE_SPEED() + 1); // Випадкова кількість кроків (0..maxSteps)
                    int[] newCoordinates = getTargetCell(island, i, j, steps);

                    // Видалення тварини зі старої клітинки
                    cell.removeEntity(animal);

                    // Додавання тварини до нової клітинки
                    island[newCoordinates[0]][newCoordinates[1]].addEntity(animal);

//                    System.out.printf("Тварина %s перемістилася з [%d, %d] до [%d, %d]%n",
//                            animal.getClass().getSimpleName(), i, j, newCoordinates[0], newCoordinates[1]);
                }
            }
        }
    }


    // Вибір випадкової сусідньої клітинки
    private static int[] getTargetCell(Cell[][] island, int startX, int startY, int steps) {
        int width = island[0].length;
        int height = island.length;

        int x = startX;
        int y = startY;


        for (int step = 0; step < steps; step++) {
            List<int[]> neighbors = new ArrayList<>();

            if (x > 0) neighbors.add(new int[]{x - 1, y}); // Верхня клітинка
            if (x < height - 1) neighbors.add(new int[]{x + 1, y}); // Нижня клітинка
            if (y > 0) neighbors.add(new int[]{x, y - 1}); // Ліва клітинка
            if (y < width - 1) neighbors.add(new int[]{x, y + 1}); // Права клітинка

            int[] next = neighbors.get(new Random().nextInt(neighbors.size())); // Вибір випадкової сусідньої клітинки
            x = next[0];
            y = next[1];
        }

        return new int[]{x, y}; // Координати кінцевої клітинки
    }

    @Override
    public void Reproduction() {

    }
}
