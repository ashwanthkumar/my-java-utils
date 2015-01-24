package in.ashwanthkumar.utils.lang.option;

/**
 * Safely wrap around null values
 * @param <T>
 */
abstract public class Option<T> {
    protected T data;

    public Option(T value) {
        this.data = value;
    }

    public T getOrElse(T defaultValue) {
        if (isEmpty()) return defaultValue;
        else return data;
    }

    public boolean isDefined() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return data == null;
    }

    public abstract T get() throws IllegalStateException;

    public static <T> Option<T> option(T value) {
        if (value == null) return new None<T>();
        else return new Some<T>(value);
    }

}
