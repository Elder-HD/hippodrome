import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    public void constructor_ShouldThrowIllegalArgumentException_whenNullNamePassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "","\n","\t"})
    public void constructor_ShouldThrowIllegalArgumentException_whenEmptyNamePassed(String argument) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 1, 2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_ShouldThrowIllegalArgumentException_whenNegativeSpeedPassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1, 2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_ShouldThrowIllegalArgumentException_whenNegativeDistancePassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -2));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameReturnCorrectName() {
        Horse horse = new Horse("Amigo", 1, 2);
        assertEquals("Amigo", horse.getName());

    }

    @Test
    void getSpeedReturnCorrectSpeed() {
        Horse horse = new Horse("Amigo", 1, 2);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistanceReturnCorrectDistance() {
        Horse horse = new Horse("Amigo", 1, 2);
        assertEquals(2, horse.getDistance());

    }

    @Test
    void getDistanceReturnZeroDistanceByDefault() {
        Horse horse = new Horse("Amige", 1);
        assertEquals(0, horse.getDistance());

    }

    @Test
    void moveCallsGetRandomDoubleMethod() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("TestName", 1, 2);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.5, 0.9, 1, 15, 200})
    void moveCalculatesFormulaCheck(double fakeRandomDouble) {
        int speed = 1;
        int distance = 2;
        Horse horse = new Horse("TestName", speed, distance);
        double expectedValue = distance + speed * fakeRandomDouble;

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(fakeRandomDouble);
            horse.move();
        }

        assertEquals(expectedValue, horse.getDistance());

    }
}