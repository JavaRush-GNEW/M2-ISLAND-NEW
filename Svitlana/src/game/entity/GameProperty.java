package game.entity;

import java.util.List;

public class GameProperty {
    private int areaWidth;
    private int areaHeight;
    private int simulationTime;
    private List<String> animals;

    public GameProperty() {
    }

    public GameProperty(int areaWidth, int areaHeight, int simulationTime) {
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        this.simulationTime = simulationTime;
    }

    public int getAreaWidth() {
        return areaWidth;
    }

    public int getAreaHeight() {
        return areaHeight;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public List<String> getAnimals() {
        return animals;
    }
}
