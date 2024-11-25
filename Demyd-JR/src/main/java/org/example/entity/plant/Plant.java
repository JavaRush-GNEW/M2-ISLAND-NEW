package org.example.entity.plant;

import org.example.entity.LivingEntity;

public abstract class Plant extends LivingEntity {
    private String name;

    public Plant(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
