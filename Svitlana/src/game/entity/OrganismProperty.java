package game.entity;

import java.util.Map;

public class OrganismProperty {
    private  double weight;
    private  double maxCellQuantity;
    private  int maxStep;
    private  double maxFoodWeight;
    //private  boolean isHunter;

    //public Map<Organism, Integer> nutritionMap;

    public OrganismProperty() {
    }

    public OrganismProperty(double weight, double foodWeight, int maxStep, double maxFoodWeight) {
        this.weight = weight;
        this.maxCellQuantity = foodWeight;
        this.maxStep = maxStep;
        this.maxFoodWeight = maxFoodWeight;
        //this.isHunter = isHunter;
    }

    public double getWeight() {
        return weight;
    }

    public double getMaxCellQuantity() {
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
