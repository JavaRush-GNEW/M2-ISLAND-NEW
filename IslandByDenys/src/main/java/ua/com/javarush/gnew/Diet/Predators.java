package ua.com.javarush.gnew.Diet;

public interface Predators  {
     default boolean isPredator() {
        return false;
    }
}
