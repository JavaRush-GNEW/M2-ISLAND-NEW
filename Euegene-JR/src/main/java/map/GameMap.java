package map;

public interface GameMap {

    void start() throws InterruptedException;

    boolean isValidCell(int row, int coll);

    Location getLocation(int row, int coll);


}
