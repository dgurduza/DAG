package dag.interfaces;

import dag.BoundBox;
import dag.Coord2D;

/**
 * Интерфейс для объединения общего функционала.
 */
public interface Coord2DInterface {
    Coord2D getPosition();

    void setPosition(Coord2D newValue);

    BoundBox bounds();
}
