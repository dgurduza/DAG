package dag;

import dag.interfaces.Coord2DInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класс, описывающий ограничивающий прямоугольник.
 */
public final class BoundBox {
    private Coord2D leftBound;
    private Coord2D rightBound;
    private Set<Coord2DInterface> leavesSet = new LinkedHashSet<>();

    /**
     * Конструктор для физических точек.
     *
     * @param point объект, описывающий точку.
     */
    public BoundBox(Coord2D point) {
        if (point != null) {
            rightBound = new Coord2D(point.getX(), point.getY());
            leftBound = new Coord2D(point.getX(), point.getY());
        } else {
            throw new NullPointerException("Point is null");
        }
    }

    /**
     * Конструктор для систем координат.
     *
     * @param children множество дочерних объектов.
     * @param position точка, начало системы координат.
     */
    public BoundBox(Set<Coord2DInterface> children, Coord2D position) {
        if (position != null) {
            if (children.size() > 0) {
                getLeaves(children, position);
                if (leavesSet.size() > 0) {
                    ArrayList<Coord2DInterface> extremum = getExtremum(leavesSet);
                    rightBound = new Coord2D(extremum.get(0).getPosition().getX(), extremum.get(1).getPosition().getY());
                    leftBound = new Coord2D(extremum.get(2).getPosition().getX(), extremum.get(3).getPosition().getY());
                }
            }
        } else {
            throw new NullPointerException("Position is null");
        }
    }

    /**
     * Получение точки правого угла.
     *
     * @return точку.
     */
    public Coord2D getRightBound() {
        Coord2D copyValue = rightBound;
        return copyValue;
    }

    /**
     * Получение точки левого угла.
     *
     * @return точку.
     */
    public Coord2D getLeftBound() {
        Coord2D copyValue = leftBound;
        return copyValue;
    }

    /**
     * Получение физических точек.
     *
     * @param children множество дочерних объектов.
     * @param position точка начала координат.
     */
    private void getLeaves(Set<Coord2DInterface> children, Coord2D position) {
        // Получение координат листьев.
        for (Coord2DInterface element : children) {
            if (element.getClass().getName().equals("dag.Origin")
                    && ((Origin) element).getChildren().size() != 0) {
                getLeaves(((Origin) element).getChildren(),
                        position.getSum(element.getPosition()));
            } else if (element.getClass().getName().equals("dag.Point")) {
                Coord2DInterface point = new Point();
                point.setPosition(element.getPosition().getSum(position));
                leavesSet.add(point);
            }
        }
    }

    /**
     * Полчение максимальных и минимальных значений Х и Y.
     *
     * @param leaf множество листьев(физических точек).
     * @return список точек.
     */
    private ArrayList<Coord2DInterface> getExtremum(Set<Coord2DInterface> leaf) {
        Coord2DInterface maxValueX = leaf.iterator().next();
        Coord2DInterface minValueX = leaf.iterator().next();
        Coord2DInterface maxValueY = leaf.iterator().next();
        Coord2DInterface minValueY = leaf.iterator().next();
        // Поиск координат.
        for (Coord2DInterface element : leaf) {
            if (maxValueX.getPosition().getX() < element.getPosition().getX()) {
                maxValueX = element;
            }
            if (maxValueY.getPosition().getY() < element.getPosition().getY()) {
                maxValueY = element;
            }
            if (minValueX.getPosition().getX() > element.getPosition().getX()) {
                minValueX = element;
            }
            if (minValueY.getPosition().getY() > element.getPosition().getY()) {
                minValueY = element;
            }
        }
        // Получение множества точек с максимальными значениями Х и Y.
        ArrayList<Coord2DInterface> set = new ArrayList<>();
        set.add(maxValueX);
        set.add(maxValueY);
        set.add(minValueX);
        set.add(minValueY);
        return set;
    }

}
