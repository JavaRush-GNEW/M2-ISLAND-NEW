package game.entity.island;

import game.entity.GameProperty;
import game.utils.GamePropertyUtil;

import java.io.IOException;

public class Island {
    private int height;
    private int width;

    private final Area[][] areas;

    public static final GameProperty GAME_PROPERTY;
    public static final String GAME_PROPERTY_XML = "game_property.xml";

    static{
        try {
            GAME_PROPERTY = GamePropertyUtil.readGameProp(GAME_PROPERTY_XML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Island(int height, int width) {
        this.areas = new Area[height][width];
    }

    public void initialPopulation() {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                areas[i][j] = new Area();
            }
        }
    }

    public void simulateLivingOnIsland() {

    }
}
