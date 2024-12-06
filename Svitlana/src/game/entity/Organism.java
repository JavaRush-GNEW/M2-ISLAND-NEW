package game.entity;

public interface Organism extends Reproducible {
    OrganismProperty getProperties();
    String getImage();
}
