package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Random;

public abstract class ChewingGrass extends Animal {

    public void eat(Cell cell) {
        Grass grass = cell.getGrass().stream().findFirst().orElse(null);
        if (grass != null){
            grass.die();
            lifePower += 10;
        }


    }

}
