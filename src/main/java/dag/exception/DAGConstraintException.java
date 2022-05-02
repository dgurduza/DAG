package dag.exception;
/**
 * Ошибка для вывода сообщения о появлении цикличности.
 */
public class DAGConstraintException extends Exception {

    public DAGConstraintException(String message) {
        super(message);
    }
}
