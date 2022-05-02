package dag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    @Test
    void setPositionThrowsNullPointerExceptionWhenCoordinateIsNull() {
        Point value = new Point();
        assertThrows(NullPointerException.class, () -> value.setPosition(null));
    }

    @Test
    void getPositionIsNullWhenUserHasNotSetCoordinates() {
        Point value = new Point();
        assertNull(value.getPosition());
    }

    @Test
    void boundsThrowsNullPointerExceptionWhenPositionIsNull() {
        Point value = new Point();
        assertThrows(NullPointerException.class, () -> value.bounds());
    }
}
