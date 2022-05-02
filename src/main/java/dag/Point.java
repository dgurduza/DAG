package dag;

import dag.interfaces.Coord2DInterface;

/**
 * Класс, описывающий физическую точку, не имеющую дочерних объектов.
 */
public class Point implements Coord2DInterface {
    private Coord2D position;

    /**
     * Передача пользователю точки.
     *
     * @return точка.
     */
    @Override
    public Coord2D getPosition() {
        return position;
    }

    /**
     * Изменение точки.
     *
     * @param newCoordinates новая точка.
     */
    @Override
    public void setPosition(Coord2D newCoordinates) {
        if (newCoordinates != null) {
            position = newCoordinates;
        } else {
            throw new NullPointerException("Coordinate is null");
        }
    }

    /**
     * Получение ограничивающего прямоугольника.
     *
     * @return ограничивающий прямоугольник.
     */
    @Override
    public BoundBox bounds() {
        if (position != null) {
            return new BoundBox(position);
        } else {
            throw new NullPointerException("Position is null");
        }
    }
}
