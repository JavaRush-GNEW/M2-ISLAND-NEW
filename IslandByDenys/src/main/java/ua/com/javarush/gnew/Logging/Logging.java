package ua.com.javarush.gnew.Logging;

public interface Logging {
    default void log(String message) {
        System.out.println("[Animal] " + message);
    }
}
