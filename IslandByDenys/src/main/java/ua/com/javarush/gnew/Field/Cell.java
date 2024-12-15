package ua.com.javarush.gnew.Field;

import lombok.Getter;
import lombok.Setter;
import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Plant.Plant;
@Getter
@Setter
public class Cell {
    private int x;
    private int y;
    private Animal animal;
    private Plant plant;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.animal = null;
        this.plant = null;
    }
}






