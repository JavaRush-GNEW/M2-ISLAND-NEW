# Animal Life Simulator

## Overview  
This project simulates the life of animals on an island. Animals interact with their environment by moving, reproducing, eating plants or other animals depending on their diet type (herbivores, carnivores, or omnivores). Plants grow independently, creating a dynamic ecosystem.

---

## Multithreading  
The simulation employs multithreading to manage different aspects of the ecosystem:  
- **Animals**: Live and interact on the island in one thread.  
- **Plants**: Grow in a separate thread.  
- **Statistics**: Generated and displayed in a third thread.

---

## Statistics  
The simulation provides comprehensive data for each type of organism, including plants. The statistics include:  
- Initial population count.  
- Current population count.  
- Number of organisms born.  
- Number of organisms that successfully hunted.  
- Number of organisms that died of starvation.  
- Number of organisms eaten by others.  
- Total deaths.  

Additionally, cumulative statistics for all organisms are displayed.

---

## Implementation  

### Island Structure  
The island is represented as a grid of locations, each populated with various animals and plants.  

### Database  
Initial parameters for each organism are stored in a dedicated `DataBase` class. These parameters are loaded from YAML files into a `Record` class, which holds the configuration for each organism.

### Initialization  
Island initialization involves creating locations and randomly populating them with organisms.  

---

### Lifecycle  

#### Animals  
Animal behavior is controlled by various controllers, each handling a specific action:  
1. **Movement**: Animals move randomly between locations.  
2. **Reproduction**: Animals find a mate and reproduce with a certain probability, producing a random number of offspring.  
3. **Feeding**: Animals search for food based on a predefined diet matrix, indicating what they can eat and the likelihood of success.  
4. **Starvation**: Animals have a hunger level that decreases over time. If it reaches zero, the animal dies.

After completing these actions, the cycle repeats.

#### Plants  
Plants grow independently in their own thread, unaffected by animal actions.

---

## Tools and Technologies  
- **Streams**: All organism iterations use the Stream API for efficient processing.  
- **Reflections**: Used for initialization and dynamic behavior implementation.  
- **YAML**: Configuration files for organisms and island parameters, parsed using Jackson libraries.  
- **Annotations**: Facilitate mapping between YAML data and class structures.  
- **Lombok**: Simplifies code with boilerplate-reducing annotations.  
- **SOLID Principles**: Ensured modularity and scalability through reflection, annotations, and abstraction.

---

## Flexibility  
The system is designed for extensibility:  
- New organisms can be added with minimal changes.  
- Actions and logic can be modified or expanded easily.

---

## Conclusion  
The project offers a unique perspective on simulating an ecosystem. Developing it required rewriting logic multiple times, which provided valuable experience in using libraries and designing modular code. Although the project is still in progress, future updates aim to optimize the simulation further.

Thank you for your interest!
