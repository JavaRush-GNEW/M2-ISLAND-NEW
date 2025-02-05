package game.domain;

public @interface Properties {
    String filename();
    boolean isHunter() default false;
}
