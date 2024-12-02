package org.example.entity.animal.interfaces;

import org.example.entity.animal.Animal;
import org.example.entity.map.Cell;

public interface Reproduction {
    default void reproduce(Cell currentCell){
       if(canReproduce()){
           Animal newAnimal = createNewAnimal();
           if (currentCell.addEntity(newAnimal) & currentCell.getEntities().size() < newAnimal.getMaxPerCellAnimal()) {
//               System.out.println(getNameAnimal() + " розмножився у клітинці.");
           } else {
//               System.out.println("Не вдалося додати " + getNameAnimal() + " через обмеження.");
           }
       }else {
//           System.out.println("Can't reproduce");
       }
   };
   boolean canReproduce();
   Animal createNewAnimal();
   String getNameAnimal();

}
