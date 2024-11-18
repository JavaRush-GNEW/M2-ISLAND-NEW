package ua.com.javarush.gnew.entity.chewingGrass;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Random;

public abstract class ChewingGrass extends Animal {

    public void eat(Grass grass) {
       grass.die();
        lifePower += 10;
    }

}
