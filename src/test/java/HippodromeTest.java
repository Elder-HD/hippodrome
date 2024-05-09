import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class HippodromeTest {

    @Test
    public void constructor_ShouldThrowIllegalArgumentException_whenNullPassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructor_ShouldThrowIllegalArgumentException_whenEmptyValuePassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.EMPTY_LIST));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ReturnListWithCorrectValues() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse_" + i,1));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertNotNull(hippodrome.getHorses());
        assertEquals("Horse_0", hippodrome.getHorses().get(0).getName());
        assertEquals("Horse_5", hippodrome.getHorses().get(5).getName());
        assertEquals("Horse_15", hippodrome.getHorses().get(15).getName());
        assertEquals(30, horses.size());


    }

    @Test
    void move_invokeMoveForAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            horses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Horse_1",1,20);
        Horse horse2 = new Horse("Horse_2",1,100);
        Horse horse3 = new Horse("Horse_3",1,5);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3));

        assertEquals(horse2,hippodrome.getWinner());
        assertEquals(100,hippodrome.getWinner().getDistance() );
    }
}