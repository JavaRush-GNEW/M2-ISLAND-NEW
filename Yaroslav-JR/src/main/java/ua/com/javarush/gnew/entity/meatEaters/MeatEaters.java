package ua.com.javarush.gnew.entity.meatEaters;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.chewingGrass.ChewingGrass;
import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Random;

public abstract class MeatEaters extends Animal {

    public void eat(ChewingGrass prey) {
        lifePower += 10;
        prey.die();
    }

    public abstract void eat(Sheep prey);
}
