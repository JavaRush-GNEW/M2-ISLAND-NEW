package game.entity;

import java.util.Map;

public class OrganismProperty {
    private  double weight;
    private  int maxCellQuantity;
    private  int maxStep;
    private  double maxFoodWeight;
    //private  boolean isHunter;

    //public Map<Organism, Integer> nutritionMap;

    public OrganismProperty() {
    }

    public OrganismProperty(double weight, int foodWeight, int maxStep, double maxFoodWeight) {
        this.weight = weight;
        this.maxCellQuantity = foodWeight;
        this.maxStep = maxStep;
        this.maxFoodWeight = maxFoodWeight;
        //this.isHunter = isHunter;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxCellQuantity() {
        return maxCellQuantity;
    }

    public int getMaxStep() {
        return maxStep;
    }

    public double getMaxFoodWeight() {
        return maxFoodWeight;
    }

//    public boolean isHunter() {
//        return isHunter;
//    }
}
