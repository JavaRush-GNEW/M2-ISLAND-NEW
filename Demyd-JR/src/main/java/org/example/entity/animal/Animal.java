package org.example.entity.animal;

import org.example.entity.LivingEntity;

public abstract class Animal extends LivingEntity {
    protected int energy;

//    public abstract void eat();
//    public abstract void move();
//    public abstract void reproduce();
    public boolean isAlive(){
        return energy > 0;
    }
}
