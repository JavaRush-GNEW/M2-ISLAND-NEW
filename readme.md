# JavaRush M2 Final project group `NEW`
# Island Simulation

## Description

Island Simulation is a Java-based ecosystem simulation that models interactions between various animal species on an island. The simulation includes both herbivores and predators, showcasing behaviors such as movement, feeding, reproduction, and death. Additionally, the system gathers and displays statistical data about the animal populations over time.

## Features

- **Dynamic Ecosystem**: Simulates interactions between multiple animal species, including herbivores and predators.
- **Multithreaded Simulation**: Utilizes multiple threads for efficient processing of island cells.
- **Custom Exception Handling**: Implements user-defined exceptions for robust error management.
- **YAML Configuration**: Configurable settings through a `config.yaml` file using Jackson.
- **Statistics Collection**: Periodic collection and display of population statistics.
- **Reflection API**: Dynamically loads animal classes using annotations and reflection.
- **Logging**: Provides console outputs for monitoring simulation events.

## Technologies

- **Java**
- **Jackson** (for YAML parsing)
- **Lombok**
- **Reflections** (for class scanning)
- **Concurrency Utilities** (for multithreading)

## Configuration

All simulation parameters are defined in the `config.yaml` file located in the `src/main/resources` directory.

### Example `config.yaml`:

```yaml
island:
  width: 120
  height: 80
  startGrassAmountPerCell: 20
  growGrassCount: 20
```
Configuration Parameters:

width: Width of the island grid.
height: Height of the island grid.
startGrassAmountPerCell: Initial amount of grass in each cell.
growGrassCount: Amount of grass that grows in each cell during each simulation cycle.
How It Works
Initialization: The island grid is initialized based on the dimensions specified in config.yaml. Each cell starts with a defined amount of grass and a random assortment of animals.

Simulation Loop:

Movement: Animals move across the island based on their movement capabilities.
Feeding: Herbivores consume grass, while predators hunt herbivores.
Reproduction: Animals may reproduce based on predefined probabilities.
Death: Animals die if their satiety reaches zero.
Statistics Collection: At regular intervals, the simulation gathers data on the population of each animal species and displays the statistics.

