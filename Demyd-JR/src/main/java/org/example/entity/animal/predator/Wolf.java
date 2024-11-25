package org.example.entity.animal.predator;

public class Wolf extends Predator{
    public Wolf() {
        super("Wolf");
    }

    @Override
    public void hunt() {
        System.out.println("Wolf is hunt!");
    }
}
