package ua.com.javarush.gnew.FindPrey;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;

public interface PredatorsFindPrey {
    default  <T extends Animal> T findPrey(Class<T> preyClass, Cell[][] cells, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(cells.length - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(cells[0].length - 1, y + 1); j++) {
                Animal potentialPrey = cells[i][j].getAnimal();
                if (preyClass.isInstance(potentialPrey) && potentialPrey.isAlive()) {
                    return preyClass.cast(potentialPrey);
                }
            }
        }
        return null;
    }
}
