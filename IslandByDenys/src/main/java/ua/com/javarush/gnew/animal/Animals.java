package ua.com.javarush.gnew.animal;


import lombok.Data;
import ua.com.javarush.gnew.Field.Field;

@Data
public abstract class Animals {
 private int weight;
 //private int quantity;
 private int speed;
 private int saturation;

 public Animals(int weight, int saturation, int speed){
  this.weight = weight;
  //this.quantity = quantity;
  this.speed = speed;
  this.saturation = saturation;
 }

    public abstract boolean Eating();

    public abstract void move(Field field, int deltaX, int deltaY);

    public abstract boolean reproduction();

    public abstract boolean death();
}
