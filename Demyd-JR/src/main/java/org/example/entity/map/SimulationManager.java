package org.example.entity.map;



import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.animal.predator.Predator;

public class SimulationManager {
    private final Object monitor = new Object();
    private boolean movingDone = false;
    private boolean huntingDone = false;
    private boolean running = true;
    private final Island island;

    public SimulationManager(int rows, int cols) {
        this.island = new Island(rows, cols);
    }

    public void startSimulation() {
        // Create Thread //
        Thread movingThread = new Thread(this::simulateMoving);
        Thread huntingThread = new Thread(this::simulateHunting);
        Thread reproductionThread = new Thread(this::simulateReproduction);

        // Starts //
        movingThread.start();
        huntingThread.start();
        reproductionThread.start();

        while (isRunning()) {
            synchronized (monitor) {
                if (checkEndCondition()) {
                    running = false;
                    System.out.println("Симуляція завершена. Лишився один вид тварин.");
                    System.out.close();
                }
            }
        }
    }

    private void simulateMoving() {
        while (isRunning()) {
            synchronized (monitor) {
                try {
                    System.out.println("Тварини рухаються...");
                    island.moveAllAnimals(island.getGRID());
                    movingDone = true;
                    monitor.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                    running = false;
                    monitor.notifyAll();
                }
            }
        }
    }

    private void simulateHunting() {
        while (isRunning()) {
            synchronized (monitor) {
                while (!movingDone) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Полювання...");
                island.huntAllAnimals();
                huntingDone = true;
                monitor.notifyAll();
            }
        }
    }

    private void simulateReproduction() {
        while (isRunning()) {
            synchronized (monitor) {
                while (!huntingDone) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Розмноження...");
                island.handleReproduction();
                movingDone = false;
                huntingDone = false;
                monitor.notifyAll();
            }
        }
    }

    private boolean isRunning() {
        synchronized (monitor) {
            return running;
        }
    }

    private boolean checkEndCondition() {
        int predatorCount = 0;
        int herbivoreCount = 0;

        for (Cell[] row : island.getGRID()) {
            for (Cell cell : row) {
                predatorCount += cell.getEntities().stream().filter(animal -> animal instanceof Predator).count();
                herbivoreCount += cell.getEntities().stream().filter(animal -> animal instanceof Herbivore).count();
            }
        }

        System.out.println("Predators: " + predatorCount + ", Herbivores: " + herbivoreCount);

        if (predatorCount == 0 || herbivoreCount == 0) {
            synchronized (monitor) {
                running = false;
                monitor.notifyAll();
            }
            return true;
        }
        return false;
    }
}

