package game.entity;


public class OrganismProperty {
    private  double weight;
    private  int maxCellQuantity;
    private  int maxStep;
    private  double maxFoodWeight;

    public OrganismProperty() {
    }

    public OrganismProperty(double weight, int foodWeight, int maxStep, double maxFoodWeight) {
        this.weight = weight;
        this.maxCellQuantity = foodWeight;
        this.maxStep = maxStep;
        this.maxFoodWeight = maxFoodWeight;
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
}
