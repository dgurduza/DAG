package dag;

import dag.exception.DAGConstraintException;
import dag.interfaces.Coord2DInterface;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceTest {
    @Test
    void getPositionIsNullWhenUserHasNotSetCoordinates() {
        Space value = new Space();
        assertNull(value.getPosition());
    }

    @Test
    void setPositionThrowsNullPointerExceptionWhenCoordinateIsNull() {
        Origin value = new Origin();
        assertThrows(NullPointerException.class, () -> value.setPosition(null));
    }

    @Test
    void getChildrenHaveSizeZeroWhenUserHasNotGetChildren() {
        Space value = new Space();
        assertTrue(value.getChildren().size() == 0);
    }

    @Test
    void setChildrenThrowsDAGConstraintExceptionWhenSetHaveCyclicality() throws DAGConstraintException {
        Space space = new Space();
        Origin value = new Origin();
        value.setPosition(new Coord2D(1, 1));
        Origin secondValue = new Origin();
        secondValue.setPosition(new Coord2D(1, 0));
        Origin thirdValue = new Origin();
        thirdValue.setPosition(new Coord2D(-2, 1));
        Set<Coord2DInterface> set = new LinkedHashSet<>();
        set.add(value);
        set.add(secondValue);
        set.add(thirdValue);
        set.add(space);
        assertThrows(DAGConstraintException.class, () -> space.setChildren(set));
    }

    @Test
    void boundsThrowsNullPointerExceptionWhenPositionIsNull() {
        Space value = new Space();
        assertThrows(NullPointerException.class, () -> value.bounds());
    }
}
