package dag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Coord2DTest {
    @Test
    void equalsReturnFalseForNull() {
        Coord2D value = new Coord2D(0, 0);
        assertFalse(value.equals(null));
    }

    @Test
    void equalsReturnTrueForEqualsCoordinates() {
        Coord2D value = new Coord2D(0, 0);
        Coord2D secondValue = new Coord2D(0, 0);
        assertTrue(value.equals(secondValue));
    }

    @Test
    void getSumThrowsNullPointerExceptionWhenSecondValueNull() {
        Coord2D value = new Coord2D(0, 0);
        Coord2D secondValue = null;
        assertThrows(NullPointerException.class, () -> value.getSum(secondValue));
    }

    @Test
    void getSumThrowsNullPointerExceptionWhenFirstValueNull() {
        Coord2D secondValue = new Coord2D(0, 0);
        Coord2D value = null;
        assertThrows(NullPointerException.class, () -> value.getSum(secondValue));
    }

    @Test
    void getXThrowsNullPointerExceptionWhenObjectIsNull() {
        Coord2D value = null;
        assertThrows(NullPointerException.class, () -> value.getX());
    }

    @Test
    void getYThrowsNullPointerExceptionWhenObjectIsNull() {
        Coord2D value = null;
        assertThrows(NullPointerException.class, () -> value.getY());
    }
}
