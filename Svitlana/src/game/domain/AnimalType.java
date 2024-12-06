package game.domain;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnimalType {
    //String name;
    boolean isHunter();
}
