package dag;

import dag.exception.DAGConstraintException;
import dag.interfaces.Coord2DInterface;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класс, описывающий мировую систему координат.
 */
public class Space implements Coord2DInterface {
    private Coord2D position;
    private Set<Coord2DInterface> children = new LinkedHashSet<>();

    /**
     * Передача пользователю координат начала системы координат.
     *
     * @return точку.
     */
    @Override
    public Coord2D getPosition() {
        return position;
    }

    /**
     * Изменение точка.
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
     * Получение множества дочерних систем координат.
     *
     * @return копию множества дочерних объектов.
     */
    public Set<Coord2DInterface> getChildren() {
        Set<Coord2DInterface> copySet = new LinkedHashSet<>(children);
        return copySet;
    }

    /**
     * Изменение множества дочерних систем координат.
     *
     * @param set новое множество дочерних объектов.
     */
    public void setChildren(Set<Coord2DInterface> set) throws DAGConstraintException {
        if (set == null) {
            throw new NullPointerException("Point is null");
        }
        // Проверка на ацикличность.
        if (!checkChildren(set, this)) {
            throw new DAGConstraintException("Cyclicality is formed");
        }
        Set<Coord2DInterface> copySet = new LinkedHashSet<>(set);
        this.children = copySet;
    }

    /**
     * Проверка множества дочерних узлов на циклы.
     *
     * @param children множество дочерних объектов.
     * @param point    точка относительно, которой проверяем.
     * @return True or False  в зависимости от того нарушилась ли ацикличность.
     */
    private boolean checkChildren(Set<Coord2DInterface> children, Coord2DInterface point) {
        if (children.size() > 0) {
            Coord2DInterface tempValue = children.iterator().next();
            int count = 0;
            // Проверка на наличие одинаковых ссылок в множестве дочерних систем.
            for (Coord2DInterface value : children) {
                if (value.equals(tempValue)) {
                    count += 1;
                }
            }
            if (count > 1) {
                return false;
            }
        }
        for (Coord2DInterface element : children) {
            if (element.getClass().getName().equals("dag.Origin")) {
                // Проверка всех созданных множеств.
                if (!checkChildren(((Origin) element).getChildren(), point)) {
                    return false;
                }
            } else if (element.getClass().getName().equals("dag.Space")) {
                return false;
            }
            if (element.equals(point)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Получение ограничивающего прямоугольника.
     *
     * @return ограничивающий прямоугольник.
     */
    @Override
    public BoundBox bounds() {
        if (position != null) {
            return new BoundBox(children, position);
        } else {
            throw new NullPointerException("Position is null");
        }
    }

}
