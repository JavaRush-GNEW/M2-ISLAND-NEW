package org.ua.com.javarush.gnew.Island;



import org.ua.com.javarush.gnew.config.Statistics;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.*;

public class IslandManager {
    private IslandMap islandMap = IslandMap.getInstance();
    private static IslandManager INSTANCE;


    private IslandManager() {
    }

    public static IslandManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IslandManager();
        }
        return INSTANCE;
    }


    public void processCell(Cell currentCell){
        synchronized (currentCell) {
            Set<Class<? extends Organism>> classes = new HashSet<>(currentCell.getResidents().keySet()); // Сет классов в клетке
            for (Class<? extends Organism> clazz : classes) {
                List<Organism> organisms = currentCell.getResidents().get(clazz);
                if (organisms == null || organisms.isEmpty()) {
                    continue;
                }
                List<Organism> copyOrganisms = new ArrayList<>(organisms);
                for (Organism organism : copyOrganisms) {
                    organism.isAnimalAlive(currentCell);
                    if (!organisms.contains(organism)) {
                        continue;
                    }

                    organism.eat(currentCell);
                    organism.reproduce(currentCell);
                    int maxSteps = organism.getMAX_STEPS_COUNT();
                    for (int i = 0; i < maxSteps; i++) {
                        organism.move(islandMap, currentCell);
                    }
                }
            }
        }
    }



    public void growGrassInCells(){
        Cell[][] cells = islandMap.getCells();
        for (Cell[] cellsArray: cells) {
            for (Cell cell: cellsArray) {
                cell.setGrassAmount(cell.getGrassAmount() + 20);
            }
        }
    }

    public void collectStatistics() {
        Statistics statistics = Statistics.getINSTANCE();
        statistics.reset();

        Cell[][] cells = islandMap.getCells();
        for (Cell[] row: cells) {
            for (Cell cell: row) {
                for (var entry: cell.getResidents().entrySet()){
                    Class<? extends Organism> clazz = entry.getKey();
                    List<Organism> list = entry.getValue();
                    int count = (list == null) ? 0: list.size();
                    if (count > 0) {
                        statistics.addCount(clazz, count);
                    }
                }
            }
        }
    }

    public void printStatistic(){
        Statistics.getINSTANCE().print();
    }
}
