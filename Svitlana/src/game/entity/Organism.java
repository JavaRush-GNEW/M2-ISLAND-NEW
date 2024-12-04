package game.entity;

public interface Organism extends Reproducible {
    //public static final OrganismProperty ORGANISM_PROPERTY = null;
    OrganismProperty getProperties();
    String getImage();
}
