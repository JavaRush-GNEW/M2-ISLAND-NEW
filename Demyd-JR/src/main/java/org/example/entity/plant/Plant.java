package org.example.entity.plant;

import org.example.entity.LivingEntity;

public abstract class Plant extends LivingEntity {
    private final String NAME_PLANT;
    private final int WEIGHT_PLANT;
    private final int MAX_PLANT_CELL;

    public Plant(String name, int weight, int maxPlantCell) {
        this.NAME_PLANT = name;
        this.WEIGHT_PLANT = weight;
        this.MAX_PLANT_CELL = maxPlantCell;
    }

    public String getNamePlant() {
        return NAME_PLANT;
    }

}
