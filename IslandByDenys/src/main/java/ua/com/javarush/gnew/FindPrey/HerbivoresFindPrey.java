package ua.com.javarush.gnew.FindPrey;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Plant.Plant;

public interface HerbivoresFindPrey {
    default  <T> T findPrey(Class<T> preyClass, Cell[][] cells, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(cells.length - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(cells[0].length - 1, y + 1); j++) {
                if (preyClass == Animal.class) {
                    Animal potentialPrey = cells[i][j].getAnimal();
                    if (potentialPrey != null && potentialPrey.isAlive()) {
                        return preyClass.cast(potentialPrey);
                    }
                } else if (preyClass == Plant.class) {
                    Plant potentialPlant = cells[i][j].getPlant();
                    if (potentialPlant != null && !potentialPlant.isEaten()) {
                        return preyClass.cast(potentialPlant);
                    }
                }
            }
        }
        return null;
    }
}
