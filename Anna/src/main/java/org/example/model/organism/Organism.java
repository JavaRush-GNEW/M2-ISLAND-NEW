package org.example.model.organism;

import org.example.model.map.Cell;

public interface Organism {
    Cell getCell();

    void setCell(Cell cell);

    boolean die();

    void lifeCycle();
}
