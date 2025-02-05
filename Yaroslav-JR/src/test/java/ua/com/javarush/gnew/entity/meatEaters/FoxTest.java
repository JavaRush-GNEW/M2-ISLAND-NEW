package ua.com.javarush.gnew.entity.meatEaters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.chewingGrass.Mouse;
import ua.com.javarush.gnew.entity.island.Cell;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FoxTest {
    private  Fox fox;
    private Cell mockCell;
    @BeforeEach
            void setUp() {
        fox = new Fox();
        mockCell = mock(Cell.class);
    }

    @Test
    void testEatSuccessful(){

        Mouse mockMouse = mock(Mouse.class);
        when(mockMouse.getWeight()).thenReturn(1.0);

        Set<Organism> mockMiceSet = new HashSet<>();
        mockMiceSet.add(mockMouse);

        Map<Class<? extends Organism>, Set<Organism>> residents = new HashMap<>();
        residents.put(Mouse.class, mockMiceSet);


        when(mockCell.getResidents()).thenReturn(residents);

        ThreadLocalRandom randomMock = mock(ThreadLocalRandom.class);
        when(randomMock.nextInt(100)).thenReturn(85);

        fox.eat(mockCell);

        verify(mockMouse).die();
        assertEquals(Fox.INITIAL_WEIGHT + 1.0, fox.getWeight());
        assertEquals(0, mockMiceSet.size());
    }

    @Test
    void testEatUnsuccessful(){

        Mouse mockMouse = mock(Mouse.class);
        when(mockMouse.getWeight()).thenReturn(1.0);

        Set<Organism> mockMi = new HashSet<>();
        mockMi.add(mockMouse);

        Map<Class<? extends Organism>, Set<Organism>> residents = new HashMap<>();
        residents.put(Mouse.class, mockMi);

        when(mockCell.getResidents()).thenReturn(residents);

        ThreadLocalRandom randomMock = mock(ThreadLocalRandom.class);
        when(randomMock.nextInt(100)).thenReturn(95);

        fox.eat(mockCell);

        verify(mockMouse, never()).die();
        assertEquals(Fox.INITIAL_WEIGHT, fox.getWeight());
        assertEquals(1, mockMi.size());

    }
    @Test
    void testPrayNotAvailable(){
        when(mockCell.getResidents()).thenReturn(Collections.emptyMap());

        fox.eat(mockCell);

        assertEquals(Fox.INITIAL_WEIGHT, fox.getWeight());
    }
}