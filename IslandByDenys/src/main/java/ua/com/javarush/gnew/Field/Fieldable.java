package ua.com.javarush.gnew.Field;

public interface Fieldable {
    default void SaveInField(int x, int y, Fieldable object) {

    }

    default Fieldable getFieldable(int x, int y) {
        return null;
    }
}
