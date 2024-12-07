package org.ua.com.javarush.gnew.model.Animals;

import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Eatable;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Movable;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Reproducible;

public abstract class Animal implements Eatable, Movable, Reproducible, Organism {
}
