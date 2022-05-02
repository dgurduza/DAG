package dag;

/**
 * Класс, описывающий математическую точку.
 */
public final class Coord2D {
    private final double x;
    private final double y;

    /**
     * Конструктор.
     *
     * @param firstCoordinate  значение координаты Х
     * @param secondCoordinate значение координаты Y
     */
    public Coord2D(double firstCoordinate, double secondCoordinate) {
        x = firstCoordinate;
        y = secondCoordinate;
    }

    /**
     * Получение координаты Х.
     *
     * @return координату Х.
     */
    public double getX() {
        return x;
    }

    /**
     * Получение координаты Y.
     *
     * @return координату Y.
     */
    public double getY() {
        return y;
    }

    /**
     * Переопределние метода для сравнивания точек по значениям.
     *
     * @param object точку с которой сравниваем нашу точку.
     * @return True or False в зависимости от равности объектов
     */
    @Override
    public boolean equals(Object object) {
        if (object != null && object.getClass() == this.getClass()) {
            return ((Coord2D) object).x == this.x && ((Coord2D) object).y == y;
        }
        return false;
    }

    /**
     * Метод для суммирования точек
     *
     * @param position точка с которой складываем нашу точку.
     * @return новую точку.
     */
    public Coord2D getSum(Coord2D position) {
        if (position != null && this != null) {
            return new Coord2D(position.getX() + this.getX(), position.getY() + this.getY());
        } else {
            throw new NullPointerException("Object is null");
        }
    }
}
