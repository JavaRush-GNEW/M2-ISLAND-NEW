package org.example;

import org.example.entity.map.SimulationManager;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SimulationManager simulationManager = new SimulationManager(5,5);
        simulationManager.startSimulation();
    }
}