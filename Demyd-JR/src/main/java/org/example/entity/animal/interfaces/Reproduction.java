package org.example.entity.animal.interfaces;

import org.example.entity.animal.Animal;
import org.example.entity.map.Cell;

public interface Reproduction {
    default void reproduce(Cell currentCell, Animal animal) {
        if (canReproduce()) {
                Animal newAnimal = createNewAnimal();
                if (currentCell.addEntity(newAnimal)) {
//                    System.out.println(getNameAnimal() + " розмножився.");
                } else {
//                    System.out.println("Не вдалося розмножити " + getNameAnimal() + ": досягнуто максимум.");
                }

        }
    }
   boolean canReproduce();
   Animal createNewAnimal();
   String getNameAnimal();

}
