import Config.IslandSimulationManager;

public class IslandSimulation {
    public static void main(String[] args) {
        IslandSimulationManager manager = new IslandSimulationManager(10, 10);
        manager.startSimulation(15, 3);
    }
}