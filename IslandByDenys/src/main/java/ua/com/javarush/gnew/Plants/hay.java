package ua.com.javarush.gnew.Plants;

public class hay extends Plants{
    @Override
    public boolean reproduction() {
        return false;
    }

    @Override
    public boolean death() {
        return false;
    }
}
