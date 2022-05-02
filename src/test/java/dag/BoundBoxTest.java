package dag;

import dag.exception.DAGConstraintException;
import dag.interfaces.Coord2DInterface;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BoundBoxTest {
    @Test
    void constructorWithTwoVariablesThrowsNullPointerExceptionWhenPositionIsNull() {
        Space space = new Space();
        assertThrows(NullPointerException.class, () -> new BoundBox(space.getChildren(), space.getPosition()));
    }

    @Test
    void constructorThrowsNullPointerExceptionWhenPositionIsNull() {
        assertThrows(NullPointerException.class, () -> new BoundBox(null));
    }

    @Test
    void leftBoundIsNullWhenChildrenSizeZero() {
        Space space = new Space();
        space.setPosition(new Coord2D(0, 0));
        assertTrue(space.getChildren().size() == 0 && space.bounds().getRightBound() == null);
    }

    @Test
    void rightBoundIsNullWhenChildrenSizeZero() {
        Space space = new Space();
        space.setPosition(new Coord2D(0, 0));
        assertTrue(space.getChildren().size() == 0 && space.bounds().getRightBound() == null);
    }

    @Test
    void rightBoundIsNotNull() throws DAGConstraintException {
        Space space = new Space();
        space.setPosition(new Coord2D(0, 0));
        Point value = new Point();
        value.setPosition(new Coord2D(1, 1));
        Set<Coord2DInterface> set = new LinkedHashSet<>();
        set.add(value);
        space.setChildren(set);
        assertNotNull(space.bounds().getRightBound());
    }

    @Test
    void leftBoundIsNotNull() throws DAGConstraintException {
        Space space = new Space();
        space.setPosition(new Coord2D(0, 0));
        Point value = new Point();
        value.setPosition(new Coord2D(1, 1));
        Set<Coord2DInterface> set = new LinkedHashSet<>();
        set.add(value);
        space.setChildren(set);
        assertNotNull(space.bounds().getLeftBound());
    }
}
