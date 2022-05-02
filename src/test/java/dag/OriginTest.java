package dag;

import dag.exception.DAGConstraintException;
import dag.interfaces.Coord2DInterface;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OriginTest {
    @Test
    void getPositionIsNullWhenUserHasNotSetCoordinates() {
        Origin value = new Origin();
        assertNull(value.getPosition());
    }

    @Test
    void setPositionThrowsNullPointerExceptionWhenCoordinateIsNull() {
        Origin value = new Origin();
        assertThrows(NullPointerException.class, () -> value.setPosition(null));
    }

    @Test
    void getChildrenHaveSizeZeroWhenUserHasNotGetChildren() {
        Origin value = new Origin();
        assertTrue(value.getChildren().size() == 0);
    }

    @Test
    void setChildrenThrowsDAGConstraintExceptionWhenSetHaveCyclicality() throws DAGConstraintException {
        Origin value = new Origin();
        Origin secondValue = new Origin();
        secondValue.setPosition(new Coord2D(1, 0));
        Origin thirdValue = new Origin();
        thirdValue.setPosition(new Coord2D(-2, 1));
        Set<Coord2DInterface> set = new LinkedHashSet<>();
        set.add(secondValue);
        set.add(thirdValue);
        set.add(value);
        assertThrows(DAGConstraintException.class, () -> value.setChildren(set));
    }

    @Test
    void boundsThrowsNullPointerExceptionWhenPositionIsNull() {
        Origin value = new Origin();
        assertThrows(NullPointerException.class, () -> value.bounds());
    }
}
