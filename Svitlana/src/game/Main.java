package game;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import game.entity.GameProperty;
import game.entity.animal.Duck;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.entity.Organism;
import game.entity.OrganismProperty;
import game.entity.island.Island;
import game.utils.GamePropertyUtil;
import game.utils.OrganismPropertyUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        OrganismProperty organismProperty = new ObjectMapper().readValue(new File("C:\\Java\\Projects\\M2-ISLAND\\M2-ISLAND-Svitlana\\Svitlana\\src\\resources\\duck.json"), OrganismProperty.class);
        System.out.println(organismProperty);
        double maxCellQuantity = organismProperty.getMaxCellQuantity();
        System.out.println(maxCellQuantity);
        Map<Class<? extends Organism>, Integer> nutritionMap = new YAMLMapper().readValue(new File("C:\\Java\\Projects\\M2-ISLAND\\M2-ISLAND-Svitlana\\Svitlana\\src\\resources\\duck_nutrition.yaml"), Map.class);
        System.out.println("C:\\Java\\Projects\\M2-ISLAND\\M2-ISLAND-Svitlana\\Svitlana\\src\\resources\\duck_nutrition.yaml");
        System.out.println(nutritionMap);

        System.out.println("=".repeat(20));
        System.out.println(OrganismPropertyUtil.readOrganismProp(Duck.class));
        System.out.println(GamePropertyUtil.readGameProp("game_property.xml").getSimulationTime());
        System.out.println(GamePropertyUtil.readGameProp("game_property.xml").getAnimals());

        System.out.println("=".repeat(20));
        System.out.println(new Duck().getProperties());
        System.out.println("*".repeat(20));

        GameProperty gameProperty = GamePropertyUtil.readGameProp("game_property.xml");
        new Island(gameProperty.getAreaWidth(), gameProperty.getAreaHeight());
    }
}